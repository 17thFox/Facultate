<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Index.aspx.cs" Inherits="Index" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    
    <script language="javascript" type="text/javascript">
        function CopyToHidden() {
            var hdElement = document.getElementById('<%= inputVal.ClientID %>');
            var divElement = document.getElementById('<%= screen.ClientID %>');
            if (hdElement != null) {
                hdElement.value = divElement.innerText;
            }
            return true;
        }
    </script>

    <form id="form1" runat="server">

        <div>
            <link rel="stylesheet" href="CalculatorStyle.css" />

            <div id="calculator">
                <!-- Screen and clear key -->
                <div class="top">
                    <span class="clear">C</span>
                    <div class="screen" runat="server" id="screen"></div>
                    <asp:HiddenField ID="inputVal" runat="server" />
                </div>

                <div class="keys">
                    <!-- operators and other keys -->
                    <span>7</span>
                    <span>8</span>
                    <span>9</span>
                    <span class="operator">+</span>
                    <span>4</span>
                    <span>5</span>
                    <span>6</span>
                    <span class="operator">-</span>
                    <span>1</span>
                    <span>2</span>
                    <span>3</span>
                    <span class="operator">/</span>
                    <span>0</span>
                    <span>.</span>
                    <asp:Button CssClass="eval" runat="server" OnClick="calculatorButton_Click" OnClientClick="return CopyToHidden()" ID="equalButton" Text="="></asp:Button>
                    <span class="operator">x</span>
                </div>
            </div>
            
            <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString %>" SelectCommand="SELECT [equation], [user] FROM [history] ORDER BY [Id] DESC">
            </asp:SqlDataSource>
            <asp:GridView runat="server" DataSourceID="SqlDataSource1" ID="historyGridView" BorderWidth="0"></asp:GridView>

            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
            <script type="text/javascript" src="CalculatorScript.js"></script>
        </div>
    </form>
</asp:Content>
