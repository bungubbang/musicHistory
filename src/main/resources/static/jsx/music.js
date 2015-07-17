var Music = React.createClass({
  handleClick: function(event) {
    console.log('click!');
  },
  render: function () {
    var formatScore = numeral(this.props.music.score).format('0,0');
    return (
      <li className="music-li" onClick={ this.handleClick } key={ this.props.music.rank }>
        <h1 className="pull-right music-score">{ formatScore }</h1>
        <div className="music-rank pull-left">
          <h1 className="big-text pull-right">{ this.props.music.rank }</h1>
        </div>
        <img className="pull-left music-album-image" src={this.props.music.albumImage}/>
        <p>{ this.props.music.songName }</p>
        <p>{ this.props.music.singer }</p>
      </li>
    )
  }
});