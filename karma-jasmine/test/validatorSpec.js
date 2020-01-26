describe("validator", function () {

    beforeAll(function () {
        $('body').append(`
        <div id="name">0ala</div>
        <div id="number">72</div>
        <div id="part1" class="partial">0ab</div>
        <div id="part2" class="partial">?cd</div>
        `);
    });

    it("invalid text", function () {
      $('#name').validate(/^[A-Z][a-z]+/);
      expect($('#name').hasClass("invalid")).toBeTrue();
    });

    it("valid text", function () {
      $('#number').validate(/^\d+/);
      expect($('#number').hasClass("invalid")).toBeFalse();
    });

    it("partialy valid", function () {
      $('.partial').validate(/^\d[a-z]*/);
      expect($('#part1').hasClass("invalid")).toBeFalse();
      expect($('#part2').hasClass("invalid")).toBeTrue();
    });

});