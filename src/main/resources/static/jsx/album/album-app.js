var AlbumApp = React.createClass({
  getInitialState: function () {
    return {
      albums : this.props.data.content,
      page : this.props.data.number,
      last : this.props.data.last
    };
  },
  addItems: function(event) {
    var that = this;
    if(!this.state.last) {
      $.getJSON('/api/album?size=100&page=' + (this.state.page + 1) , function( data ) {
        that.setState({
          albums: that.state.albums.concat(data.content),
          page : data.number,
          last : data.last
        });
      });
    }
  },
  doSearch:function(queryText){
    var that = this;
    $.getJSON('/api/album?size=100&page=0&name=' + queryText, function( data ) {
      that.setState({
        albums: data.content,
        page : data.number,
        last : data.last,
        moreDisplay: { display:'none'}
      });
    });
  },
  render: function () {
    return(
      <div>
        <div>
          <SearchBox doSearch={this.doSearch}/>
        </div>
        <div>
          <AlbumList albums={ this.state.albums }/>
        </div>
        <div>
          <MusicMoreButton addItems={this.addItems} moreDisplay={ this.state.moreDisplay }/>
        </div>
      </div>
    )
  }
});