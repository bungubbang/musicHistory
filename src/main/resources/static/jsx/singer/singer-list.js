var SingerList = React.createClass({
  getInitialState: function () {
    return this.props;
  },
  render: function () {
    var createItem = function(singer, index) {
      return <Singer singer = {singer} key = {singer.rank}/>;
    };
    return <ul className="music-ul">{this.props.singers.map(createItem)}</ul>;
  }
});