Kakao.init('62bdc0a1476d4bd8afeeb6883af27f82');

var shareStory = function() {
  Kakao.Story.share({
    url: location.href
  });
};

var windowHeight = 360;
var windowWidth = 640;

var shareFacebook = function() {
  var REDIRECT_URL = location.href;
  var facebookUrl = "https://www.facebook.com/dialog/share?app_id=804185619702959&display=popup&href=" + REDIRECT_URL  + "&redirect_uri=" + REDIRECT_URL;
  window.open(facebookUrl, "_blank", "height=" + windowHeight + ",width=" + windowWidth);
};

var shareTwitter = function() {
  var twitterUrl = "https://twitter.com/intent/tweet?url=" + location.href;
  window.open(twitterUrl, "_blank", "height=" + windowHeight + ",width=" + windowWidth);
};