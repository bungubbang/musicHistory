var Music = React.createClass({
  handleClick: function(event) {
    console.log('click!');
  },
  render: function () {
    return (
      <li onClick={ this.handleClick } key={ this.props.music.rank }>
        <h2>{ this.props.music.rank }</h2>
        <p>{ this.props.music.songName }</p>
        <p>{ this.props.music.singer }</p>
        <p>{ this.props.music.score }</p>
      </li>
    )
  }
});