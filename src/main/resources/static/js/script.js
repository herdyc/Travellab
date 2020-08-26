/*jslint browser: true*/
/*global $, jQuery*/

$(document).ready(function () {
                  
    $('.js--scroll-working').click(function () {
        'use strict';
        $('html, body').animate({scrollTop: $('.js--working').offset().top}, 1000);
    });
    
    $('.js--scroll-des').click(function () {
        'use strict';
        $('html, body').animate({scrollTop: $('.js--destinations').offset().top}, 1000);
    });
                  
});