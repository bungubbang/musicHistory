$.getJSON('/api/song?size=100&page=0', function( data ) {
  React.render(<MusicApp data={ data }/>, document.getElementById("music-list"));
});

$('.search-btn').click(function() {
    $('.react-search-input').val($('.search-input').val());
    $('.react-search-btn').click();
});

