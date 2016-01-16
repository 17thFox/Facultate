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
            user = Request.QueryString["user"];
            pass = Request.QueryString["pass"];
            name = Request.QueryString["name"];
            
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

        void register()
        {
            try
            {
                if (!userExists())
                {
                    System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
                    con.Open();
                    //insert the file into database
                    string strQuery = "insert into [dbo].[users]([username], [password], [name]) values (@username, @password, @name)";
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

    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='login.css' media='all' rel='stylesheet'>
    <link href='demo.css' media='all' rel='stylesheet'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, target-densitydpi=device-dpi'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <div class="login-card">
        <h1>Register</h1>
        <div style="text-align: center; width: 100%" runat="server" id="message"></div>
        <br>
        <form>
            <input type="text" name="user" placeholder="Username">
            <input type="password" name="pass" placeholder="Password">
            <input type="text" name="name" placeholder="Name">
            <input type="submit" name="register" class="login login-submit" value="register">
        </form>

        <div class="login-help">
            <a href="/Login.aspx">Login</a>
        </div>
    </div>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>



    <span class="resp-info"></span>


</asp:Content>

