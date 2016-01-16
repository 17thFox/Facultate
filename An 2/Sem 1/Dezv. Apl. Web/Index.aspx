<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Index.aspx.cs" Inherits="Index" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <script runat="server">
        int userId;
        void Page_Load(object sender, EventArgs e)
        {
            if (Context.User.Identity.IsAuthenticated)
            {
                signoutButton.Visible = true;
                signinButton.Visible = false;
            }
             
            else
            {
                signoutButton.Visible = false;
                signinButton.Visible = true;
            }

            userId = getUserId();
        }

        int getUserId()
        {
            if (Context.User.Identity.IsAuthenticated)
            {
                try
                {
                    System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
                    con.Open();
                    string strQuery = "select [Id] from [dbo].[users] where [username]=@username";
                    System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
                    cmd.Parameters.AddWithValue("@username", System.Data.SqlDbType.Int).Value = Context.User.Identity.GetUserName();
                    System.Data.SqlClient.SqlDataReader myReader = cmd.ExecuteReader();

                    if (myReader.Read())
                    {
                        // Assuming your desired value is the name as the 3rd field
                        int id = -1;
                        int.TryParse(myReader["Id"].ToString(), out id);
                        myReader.Close();
                        con.Close();
                        return id;
                    }

                    myReader.Close();
                    con.Close();
                }
                catch (Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine(ex.ToString());
                }
            }
            return -1;
        }
        void addToHistory(string equation)
        {
            if (userId != -1)
            {

                System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
                con.Open();
                //insert the file into database
                string strQuery = "insert into [dbo].[history]([equation], [user]) values (@equation, @user)";
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
                cmd.Parameters.Add("@equation", System.Data.SqlDbType.VarChar).Value = equation;
                cmd.Parameters.Add("@user", System.Data.SqlDbType.Int).Value = userId;
                cmd.ExecuteNonQuery();
                con.Close();
            }
        }
        
        protected void signinButton_Click(object sender, EventArgs e)
        {
            Response.Redirect("Login.aspx");

        }

        protected void signoutButton_Click(object sender, EventArgs e)
        {

            FormsAuthentication.SignOut();
            Response.Redirect("Index.aspx");

        }
        protected void calculatorButton_Click(object sender, EventArgs e)
        {
            string equation = inputVal.Value;
            if (equation.Length > 0)
            {
                equation = equation.Replace("x", "*");
                char lastChar = equation[equation.Length - 1];
                string operators = "-*+/";

                if (operators.IndexOf(lastChar) > -1 || lastChar == '.')
                {
                    equation = equation.Substring(0, equation.Length - 1);
                }

                screen.InnerText = Evaluate(equation).ToString();
                addToHistory(equation + "=" + screen.InnerText);
            }
        }
        
        double Evaluate(string expression)
        {
            var loDataTable = new System.Data.DataTable();
            var loDataColumn = new System.Data.DataColumn("Eval", typeof(double), expression);
            loDataTable.Columns.Add(loDataColumn);
            loDataTable.Rows.Add(0);
            return (double)(loDataTable.Rows[0]["Eval"]);
        }
    </script>
    <script language="javascript" type="text/javascript">
        function CopyToHidden() {
            var hdElement = document.getElementById('<%= inputVal.ClientID %>');
            var divElement = document.getElementById('<%= screen.ClientID %>');
            if (hdElement != null) {
                hdElement.value = divElement.innerText;
            }
            return true;
        }
    </script>

    <form id="form1" runat="server">

        <asp:Button ID="signoutButton" runat="server" OnClick="signoutButton_Click" Text="Sign-Out" />

        <asp:Button ID="signinButton" runat="server" OnClick="signinButton_Click" Text="Sign-In" />
        <div>
            <link rel="stylesheet" href="CalculatorStyle.css" />

            <div id="calculator">
                <!-- Screen and clear key -->
                <div class="top">
                    <span class="clear">C</span>
                    <div class="screen" runat="server" id="screen"></div>
                    <asp:HiddenField ID="inputVal" runat="server" />
                </div>

                <div class="keys">
                    <!-- operators and other keys -->
                    <span>7</span>
                    <span>8</span>
                    <span>9</span>
                    <span class="operator">+</span>
                    <span>4</span>
                    <span>5</span>
                    <span>6</span>
                    <span class="operator">-</span>
                    <span>1</span>
                    <span>2</span>
                    <span>3</span>
                    <span class="operator">/</span>
                    <span>0</span>
                    <span>.</span>
                    <asp:Button CssClass="eval" runat="server" OnClick="calculatorButton_Click" OnClientClick="return CopyToHidden()" ID="equalButton" Text="="></asp:Button>
                    <span class="operator">x</span>
                </div>
            </div>

            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
            <script type="text/javascript" src="CalculatorScript.js"></script>
        </div>
    </form>
</asp:Content>
