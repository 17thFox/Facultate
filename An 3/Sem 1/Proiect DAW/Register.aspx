<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Register.aspx.cs" Inherits="Register" %>

<%@ Import Namespace="System.Web.Security" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">

    <script runat="server">
    
        String user;
        String pass;
        String name;

        void Page_Load(object sender, EventArgs e)
        {
            if (Context.User.Identity.IsAuthenticated)
            {
                Response.Redirect("Index.aspx");
                return;
            }

            if (IsPostBack)
            {
                user = username.Text;
                pass = password.Text;
                name = firstname.Text;

                if (user != null && pass != null && user.Length > 0 && pass.Length > 0 && name != null && name.Length > 0)
                {
                    register();
                }
                else
                    if (user != null || pass != null || name != null)
                    {
                        message.InnerText = "Fill in the credentials!";
                    }
            }
        }

        void register()
        {
            try
            {
                if (!userExists())
                {
                    System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
                    con.Open();
                    //insert the file into database
                    string strQuery = "insert into [dbo].[users]([username], [password], [firstname]) values (@username, @password, @name)";
                    System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
                    cmd.Parameters.Add("@username", System.Data.SqlDbType.VarChar).Value = user;
                    cmd.Parameters.Add("@password", System.Data.SqlDbType.VarChar).Value = pass;
                    cmd.Parameters.Add("@name", System.Data.SqlDbType.VarChar).Value = name;
                    cmd.ExecuteNonQuery();
                    con.Close();

                    FormsAuthentication.RedirectFromLoginPage(user, true);
                    Response.Redirect("Index.aspx");
                }
                else
                {
                    message.InnerText = "User already exists!";
                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.ToString());
            }
        }

        Boolean userExists()
        {
            try
            {
                System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
                con.Open();
                string strQuery = "select [username] from [dbo].[users] where username=@username";
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
                cmd.Parameters.AddWithValue("@username", System.Data.SqlDbType.VarChar).Value = user;
                System.Data.SqlClient.SqlDataReader myReader = cmd.ExecuteReader();

                if (myReader.Read())
                {
                    myReader.Close();
                    con.Close();
                    return true;
                }

                myReader.Close();
                con.Close();
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.ToString());
            }
            return false;
        }
    </script>

    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css' />
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href='Login.css' media='all' rel='stylesheet' />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <div class="login-card">
        <h1>Register</h1>
        <div style="text-align: center; width: 100%; color: #ff0000" runat="server" id="message"></div>
        <br>
        <form runat="server">

            <asp:Label Text="Username" runat="server" />
            <br />
            <asp:TextBox ID="username" runat="server" />

            <asp:Label Text="Password" runat="server" />
            <br />
            <asp:TextBox ID="password" runat="server" TextMode="Password" />

            <asp:Label Text="First Name" runat="server" />
            <br />
            <asp:TextBox ID="firstname" runat="server" />

            <asp:Button ID="registerButton" CssClass="login login-submit" runat="server" Text="Register" />

        </form>

        <div class="login-help">
            <a href="/Login.aspx">Login</a>
        </div>
    </div>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>



    <span class="resp-info"></span>


</asp:Content>

