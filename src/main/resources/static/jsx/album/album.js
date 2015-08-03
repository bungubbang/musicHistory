var Album = React.createClass({

  handleClick: function(event) {
    location.href="/album/" + this.props.album.singerId;
  },
  render: function () {
    var formatScore = numeral(this.props.album.score).format('0,0');
    return (
      <li className="music-li row" onClick={ this.handleClick } key={ this.props.album.rank }>
        <div className="col-md-2">
          <h2 className="big-text pull-right">{ this.props.album.rank }</h2>
        </div>
        <div className="col-md-2">
          <img className="music-album-image" src={this.props.album.albumImage}/>
        </div>
        <div className="col-md-4">
          <h3 className="music-songName">{ this.props.album.album }</h3>
        </div>
        <div className="col-md-2">
          <h3 className="music-singer">{ this.props.album.singer }</h3>
        </div>
        <div className="col-md-2">
          <h3 className="music-score">{ formatScore }</h3>
        </div>
      </li>
    )
  }
});