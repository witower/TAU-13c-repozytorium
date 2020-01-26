(function ( $ ) {

    $.fn.validate = function(regex) {
      this.each(function() {
            var elem = $( this );
            if(regex.test(elem.text())) {
              elem.removeClass("invalid")
            }
            else {
              elem.addClass("invalid")
            }
      });
        return this;
    };

}( jQuery ));