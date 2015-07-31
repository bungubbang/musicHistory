var Singer = React.createClass({

  handleClick: function(event) {
    location.href="/singer?Id=" + this.props.singer.singerId;
  },
  render: function () {
    var formatScore = numeral(this.props.singer.score).format('0,0');
    return (
      <li className="music-li row" onClick={ this.handleClick } key={ this.props.singer.rank }>
        <div className="col-md-2">
          <h2 className="big-text pull-right">{ this.props.singer.rank }</h2>
        </div>
        <div className="col-md-2">
          <img className="music-album-image" src={this.props.singer.singerImage}/>
        </div>
        <div className="col-md-6">
          <h3 className="music-songName">{ this.props.singer.singer }</h3>
        </div>
        <div className="col-md-2">
          <h3 className="music-score">{ formatScore }</h3>
        </div>
      </li>
    )
  }
});