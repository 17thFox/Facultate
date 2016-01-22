using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Login : System.Web.UI.Page
{
    String user;
    String pass;

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
            if (user != null && pass != null && user.Length > 0 && pass.Length > 0)
            {
                if (login())
                {
                    System.Web.Security.FormsAuthentication.RedirectFromLoginPage(user, true);
                    Response.Redirect("Index.aspx");
                }
                else
                {
                    message.InnerText = "Wrong credentials!";
                }
            }
            else
                if (user != null || pass != null)
                {
                    message.InnerText = "Fill in the credentials!";
                }
        }
    }

    Boolean login()
    {
        try
        {
            System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
            con.Open();
            string strQuery = "select [username] from [dbo].[users] where username=@username and password=@password";
            System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
            cmd.Parameters.AddWithValue("@username", user);
            cmd.Parameters.AddWithValue("@password", pass);
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

}