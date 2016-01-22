using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class MyProfile : System.Web.UI.Page
{
    int userId;

    String usernameValue;
    String firstnameValue;
    String lastnameValue;
    int ageValue;
    String genderValue;

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Context.User.Identity.IsAuthenticated)
        {
            userId = getUserId();
            if (!IsPostBack)
            {
                getProfile();

                username.Text = usernameValue;
                firstname.Text = firstnameValue;
                lastname.Text = lastnameValue;
                if (ageValue > -1)
                {
                    age.Text = ageValue.ToString();
                }
                gender.Text = genderValue;
            }
            else
            {
                saveProfileClicked();
            }
        }
        else
        {
            Response.Redirect("Login.aspx");
        }
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
                cmd.Parameters.AddWithValue("@username", Context.User.Identity.Name);
                System.Data.SqlClient.SqlDataReader myReader = cmd.ExecuteReader();

                if (myReader.Read())
                {
                    // Assuming your desired value is the name as the 3rd field
                    int id = -1;
                    //id = int.Parse(myReader["Id"].ToString());
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

    private void getProfile()
    {
        if (Context.User.Identity.IsAuthenticated)
        {
            try
            {
                System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
                con.Open();
                string strQuery = "select * from [dbo].[users] where [username]=@username";
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
                cmd.Parameters.AddWithValue("@username", Context.User.Identity.Name);
                System.Data.SqlClient.SqlDataReader myReader = cmd.ExecuteReader();

                if (myReader.Read())
                {
                    usernameValue = myReader["username"].ToString();
                    firstnameValue = myReader["firstname"].ToString();
                    lastnameValue = myReader["lastname"].ToString();
                    ageValue = -1;
                    int.TryParse(myReader["age"].ToString(), out ageValue);
                    genderValue = myReader["gender"].ToString();
                }

                myReader.Close();
                con.Close();
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.ToString());
            }
        }
    }

    private void saveProfileToDatabase()
    {
        if (Context.User.Identity.IsAuthenticated)
        {
            try
            {
                System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
                con.Open();
                string strQuery = "update [dbo].[users] set [firstname]=@firstname, [lastname]=@lastname, [age]=@age, [gender]=@gender where [Id]=@Id";
                System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
                cmd.Parameters.AddWithValue("@Id", userId);
                cmd.Parameters.AddWithValue("@firstname", firstnameValue);
                cmd.Parameters.AddWithValue("@lastname", lastnameValue);
                cmd.Parameters.AddWithValue("@age", ageValue);
                cmd.Parameters.AddWithValue("@gender", genderValue);

                cmd.ExecuteNonQuery();

                con.Close();
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.ToString());
            }
        }
    }

    protected void saveProfileClicked()
    {
        if (firstname.Text != null && firstname.Text.Length > 0)
        {
            int myAge = -1;
            if (age.Text != null && age.Text.Length > 0)
            {
                try
                {
                    myAge = int.Parse(age.Text);
                }
                catch
                {
                    message.InnerText = "Age must be a number!";
                    return;
                }
                if (myAge < 0 || myAge >= 130)
                {
                    message.InnerText = "Age must be between 0 and 130!";
                    return;
                }
            }

            firstnameValue = firstname.Text;
            lastnameValue = lastname.Text;
            ageValue = myAge;
            genderValue = gender.Text;

            saveProfileToDatabase();
            Response.Redirect("Index.aspx");
        }
        else
        {
            message.InnerText = "First name can't be empty!";
        }
    }


}