using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Register : System.Web.UI.Page
{
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
                cmd.Parameters.AddWithValue("@username", user);
                cmd.Parameters.AddWithValue("@password", pass);
                cmd.Parameters.AddWithValue("@name", name);
                cmd.ExecuteNonQuery();
                con.Close();

                System.Web.Security.FormsAuthentication.RedirectFromLoginPage(user, true);
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
            cmd.Parameters.AddWithValue("@username", user);
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