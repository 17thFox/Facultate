using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Index : System.Web.UI.Page
{
    int userId;

    void Page_Load(object sender, EventArgs e)
    {
        userId = getUserId();

        refreshLayout();
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

    void addToHistory(string equation)
    {
        if (userId != -1)
        {
            System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\Database.mdf;Integrated Security=True;");
            con.Open();
            //insert the file into database
            string strQuery = "insert into [dbo].[history]([equation], [user]) values (@equation, @user)";
            System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand(strQuery, con);
            cmd.Parameters.AddWithValue("@equation", equation);
            cmd.Parameters.AddWithValue("@user", userId);
            cmd.ExecuteNonQuery();
            con.Close();

            refreshLayout();
        }
    }

    void refreshLayout()
    {
        SqlDataSource1.SelectCommand =
            "SELECT h.[equation], u.[username] " +
            "FROM [history] h " +
            "JOIN [users] u ON u.[Id] = h.[user] " +
            "ORDER BY h.[Id] DESC";
        SqlDataSource1.DataBind();
        historyGridView.DataBind();
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
}