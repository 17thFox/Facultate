$(function () {
    //alert("Hello");
    var p = 0;

    var interval;

    var imgstack = [];

    for (i = 0; i < $('.ic').children().length; i++) {
        imgstack.push($('.ic').find('span').eq(i).text());
    }

    //alert(imgstack);

    $('.mss').mouseover(function () {
        interval = setInterval(
            function changeImg() {
                if (p < imgstack.length-1) {
                    
                    var img = imgstack[p];
                    $('.thumb').attr('src', img);
                    //$('.mss').html(img);
                    p = p + 1;
                }

                else {
                    var img = imgstack[p];
                    $('.thumb').attr('src', img);
                    //$('.mss').html(img);
                    p = 0;
                }
            }
        , 1000);
    });



    $('.mss').mouseout(function () {
        clearInterval(interval);
        p = 0;
        $('.thumb').attr('src', 'Images-1.jpg');
        //$('.mss').html('Images-1.jpg');
    });
});