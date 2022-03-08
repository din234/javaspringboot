var cache = {
    data: {},
    remove: function(data){
        delete cache.data[data]
    }

};


$(function () {
    var url = '/echo/jsonp/';
    $('#ajaxButton').click(function (e) {
        $.ajax({
            url: url,
            data: {
                test: 'value'
            },
            cache: true,
            beforeSend: function () {
                if (localCache.exist(url)) {
                    doSomething(localCache.get(url));
                    return false;
                }
                return true;
            },
            complete: function (jqXHR, textStatus) {
                localCache.set(url, jqXHR, doSomething);
            }
        });
    });
});
