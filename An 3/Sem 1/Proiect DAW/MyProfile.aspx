<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="MyProfile.aspx.cs" Inherits="MyProfile" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

     <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css' />
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href='login.css' media='all' rel='stylesheet' />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <div class="login-card">
        <h1>My Profile</h1>
        <div style="text-align: center; width: 100%; color:#ff0000" runat="server" id="message"></div>
        <br />
        <form runat="server">
            <asp:Label Text="Username" runat="server"/>
            <br />
            <asp:TextBox ID="username" runat="server" Enabled="false"/>
            
            <asp:Label Text="First Name" runat="server"/>
            <br />
            <asp:TextBox ID="firstname" runat="server"/>
            
            <asp:Label Text="Last Name" runat="server"/>
            <br />
            <asp:TextBox ID="lastname" runat="server"/>

            <asp:Label Text="Age (between 0 and 130)" runat="server"/>
            <br />
            <asp:TextBox ID="age" runat="server"/>

            <asp:Label Text="Gender" runat="server"/>
            <br />
            <asp:TextBox ID="gender" runat="server"/>

            <asp:Button ID="profileButton" CssClass="login login-submit" runat="server" Text="Save"/>
        </form>

    </div>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>



    <span class="resp-info"></span>


</asp:Content>

