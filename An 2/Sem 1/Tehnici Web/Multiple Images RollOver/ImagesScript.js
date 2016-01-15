$(document).ready(function () {
    //alert("Hello");
    var p = 0;
    var interval;
    var imgstack = document.querySelectorAll('.ic span');
    var thumb = $('.thumb');
    var thumbContainer = $('.thumbContainer');

    // for(i = 0; i < imgstack.length; i++)
    // {
    //     console.log(imgstack[i]);
    // }

    //alert(imgstack);

    thumbContainer.mouseover(function () {
        interval = setInterval(
            function changeImg() {
                    var img = imgstack[ p % imgstack.length ].innerText;
                    thumb.attr('src', img);
                    p++;
            }
            , 1000);
    });



    thumbContainer.mouseout(function () {
        clearInterval(interval);
        p = 0;
        thumb.attr('src', 'Images-1.jpg');
    });
});
