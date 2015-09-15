var MusicApp = React.createClass({
    getInitialState: function () {
        return {
            musics : this.props.data.content,
            page : this.props.data.number,
            last : this.props.data.last,
            moreDisplay: {}
        };
    },
    addItems: function(event) {
        var that = this;
        $.getJSON('/api/song?size=100&page=' + (this.state.page + 1) , function( data ) {
            that.setState({
                musics: that.state.musics.concat(data.content),
                page : data.number,
                last : data.last,
                moreDisplay: {}
            });
            if(data.last) {
                that.setState({
                    moreDisplay: { display:'none'}
                })
            }
        });
    },
    doSearch:function(queryText){
        var that = this;
        $.getJSON('/api/song?size=100&page=0&name=' + queryText, function( data ) {
            that.setState({
                musics: data.content,
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
                    <MusicList musics={ this.state.musics }/>
                </div>
                <div>
                    <MusicMoreButton addItems={this.addItems} moreDisplay={ this.state.moreDisplay }/>
                </div>
            </div>
        )
    }
});