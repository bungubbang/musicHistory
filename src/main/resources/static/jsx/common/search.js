var SearchBox = React.createClass({
    getInitialState: function () {

        var $searchBtn = $('.search-btn');
        $searchBtn.click(function() {
            $('.react-search-btn').click();
        });

        $searchBtn.keyPress(function(key) {
            if (key.keyCode == 13) {
                $('.react-search-btn').click();
            }
        });


        return {
            style : {
                display: 'none'
            }
        };
    },
    handleClick: function(event) {
        this.props.doSearch($('.search-input').val());
    },
    render: function () {
        return (
            <div style={this.state.style}>
                <input type="text" className="react-search-input"/>
                <button type="button" className="react-search-btn" onClick={this.handleClick}/>
            </div>
        )
    }
});