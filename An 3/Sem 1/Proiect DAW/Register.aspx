<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Register.aspx.cs" Inherits="Register" %>

<%@ Import Namespace="System.Web.Security" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">


    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css' />
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href='Login.css' media='all' rel='stylesheet' />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <div class="login-card">
        <h1>Register</h1>
        <div style="text-align: center; width: 100%; color: #ff0000" runat="server" id="message"></div>
        <br />
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

