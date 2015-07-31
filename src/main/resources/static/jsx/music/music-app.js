var MusicApp = React.createClass({
  getInitialState: function () {
    return {
      musics : this.props.data.content,
      page : this.props.data.number,
      last : this.props.data.last
    };
  },
  addItems: function(event) {
    var that = this;
    if(!this.state.last) {
      $.getJSON('/api/song?size=100&page=' + (this.state.page + 1) , function( data ) {
        that.setState({
          musics: that.state.musics.concat(data.content),
          page : data.number,
          last : data.last
        });
      });
    }
  },
  render: function () {
    return(
      <div>
        <div>
          <MusicList musics={ this.state.musics }/>
        </div>
        <div>
          <MusicMoreButton addItems={this.addItems}/>
        </div>
      </div>
    )
  }
});