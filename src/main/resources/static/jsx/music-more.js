var MusicMoreButton = React.createClass({
  getInitialState: function () {
    return this.props;
  },
  handleClick: function(event) {
    this.props.addItems(event);
  },
  render: function () {
    return (
      <button type="button" className="btn btn-primary btn-lg col-md-6 col-md-offset-3 more-button" onClick={this.handleClick}>더 보기 ...</button>
    );
  }
});

