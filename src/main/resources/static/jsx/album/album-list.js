var AlbumList = React.createClass({
  getInitialState: function () {
    return this.props;
  },
  render: function () {
    var createItem = function(album, index) {
      return <Album album = {album} key = {album.rank}/>;
    };
    return <ul className="music-ul">{this.props.albums.map(createItem)}</ul>;
  }
});