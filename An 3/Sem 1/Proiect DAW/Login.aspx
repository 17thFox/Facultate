<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="~/Login.aspx.cs" Inherits="Login" %>

<%@ Import Namespace="System.Web.Security" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">


    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css' />
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href='Login.css' media='all' rel='stylesheet' />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <div class="login-card">
        <h1>Log-in</h1>
        <div style="text-align: center; width: 100%; color:red;" runat="server" id="message"></div>
        <br>
        <form runat="server">
            <asp:Label Text="Username" runat="server" />
            <br />
            <asp:TextBox ID="username" runat="server" />

            <asp:Label Text="Password" runat="server" />
            <br />
            <asp:TextBox ID="password" runat="server" TextMode="Password" />

            <asp:Button ID="loginButton" CssClass="login login-submit" runat="server" Text="Login" />
        </form>

        <div class="login-help">
            <a href="/Register.aspx">Register</a>
        </div>
    </div>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>



    <span class="resp-info"></span>


</asp:Content>

