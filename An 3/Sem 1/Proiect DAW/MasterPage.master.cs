using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class MasterPage : System.Web.UI.MasterPage
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Context.User.Identity.IsAuthenticated)
        {
            signInButton.Visible = false;
            signOutButton.Visible = true;
            profileButton.Visible = true;
        }
        else
        {
            signInButton.Visible = true;
            signOutButton.Visible = false;
            profileButton.Visible = false;
        }
    }
}
