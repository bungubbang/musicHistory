$.getJSON('/api/album?size=100&page=0', function( data ) {
  React.render(<AlbumApp data={ data }/>, document.getElementById("album-list"));
});

