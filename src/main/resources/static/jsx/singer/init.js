$.getJSON('/api/singer?size=100&page=0', function( data ) {
  React.render(<SingerApp data={ data }/>, document.getElementById("singer-list"));
});

