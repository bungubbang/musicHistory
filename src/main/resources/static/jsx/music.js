var Music = React.createClass({
  handleClick: function(event) {
    console.log('click!');
  },
  render: function () {
    var formatScore = numeral(this.props.music.score).format('0,0');
    return (
      <li className="music-li row" onClick={ this.handleClick } key={ this.props.music.rank }>
        <div className="col-md-2">
          <h2 className="big-text pull-right">{ this.props.music.rank }</h2>
        </div>
        <div className="col-md-2">
          <img className="music-album-image" src={this.props.music.albumImage}/>
        </div>
        <div className="col-md-4">
          <h3 className="music-songName">{ this.props.music.songName }</h3>
          <p className="music-albumName">{ this.props.music.album }</p>
        </div>
        <div className="col-md-2">
          <h3 className="music-singer">{ this.props.music.singer }</h3>
        </div>
        <div className="col-md-2">
          <h3 className="music-score">{ formatScore }</h3>
        </div>
      </li>
    )
  }
});