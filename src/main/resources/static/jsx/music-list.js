var MusicList = React.createClass({
  getInitialState: function () {
    return this.props;
  },
  render: function () {
    var createItem = function(music, index) {
      return <Music music = {music} key = {music.rank}/>;
    };
    return <ul>{this.props.musics.map(createItem)}</ul>;
  }
});