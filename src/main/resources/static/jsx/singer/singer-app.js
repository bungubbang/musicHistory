var SingerApp = React.createClass({
  getInitialState: function () {
    return {
      singers : this.props.data.content,
      page : this.props.data.number,
      last : this.props.data.last
    };
  },
  addItems: function(event) {
    var that = this;
    if(!this.state.last) {
      $.getJSON('/api/singer?size=100&page=' + (this.state.page + 1) , function( data ) {
        that.setState({
          singers: that.state.singers.concat(data.content),
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
          <SingerList singers={ this.state.singers }/>
        </div>
        <div>
          <MusicMoreButton addItems={this.addItems}/>
        </div>
      </div>
    )
  }
});