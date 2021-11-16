$(function () {
    //csrf防護
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });

    //不需判斷是否登入（我的空間已有filter攔截）
    loadPage();

    function loadPage() {

        firstPage();

    }
    function firstPage(){

        $.ajax({
            url: "/user/myFirstSearchOfPageForCollect",
            type: "GET",
            success: function (response){  //response from 後端
                console.log("第一頁文章response" + response);
                console.log("建立空的html");
                let html = "";
                console.log("文章-for迴圈開始");
                for(let myArticleAll of response){
                    //從資料庫取出文章資訊

                    html += getHtmlArticle(myArticleAll);

                    console.log("文章-for迴圈結束");
                    $("#articleBox").html(html);
                    console.log("跑完--輸入搜尋吧查詢並送出第一頁");

                    // (結束)文章換頁生成
                }
            }
        })
    };

    function getHtmlArticle(myArticleAll){
        let articleTitle = myArticleAll.articleTitle;
        console.log("articleTitle!!!" + articleTitle)
        let textEditor = myArticleAll.textEditor;
        let createDate = myArticleAll.createDate;
        let createTime = myArticleAll.createTime;
        let saveImgPath = myArticleAll.saveImgPath;
        let articleId = myArticleAll.articleId;
        const imgPath = "https://localhost:8080/";

        return `
        <!-- 文章圖片  -->
                     <div class="single-blog-area bg-gr0200 blog-style-2 mb-5 wow fadeInUp " data-wow-delay="0.2s"
                    data-wow-duration="1000ms">
                    <div class="row align-items-center">
                        <div class="col-12 col-md-6">
                            <div class="single-blog-thumbnail">
                             <img src="${ imgPath + saveImgPath}">
                            </div>
                        </div>
                        <div class="col-12 col-md-6 text-bl04">
                            <!-- 文章內容 -->
                            <div class="single-blog-content">
                                <h4><a href="https://localhost:8080/user/article/${articleTitle}" class="post-headline  btn-outline-bl01 text-bl04 fw-bold">
                                    ${articleTitle}   
                                    </a></h4>

                                <p class="text-bl04">${textEditor.substring(0,45)}...</p>
                                <div class="post-meta">
                                   <p class="text-bl04">發表於:&nbsp${createDate}&nbsp${createTime}</p>
                                    <button
                                         class="btn btn-sm btn-bl03 border-2 border-gr0200 rounded-pill text-gr0200 fw-bold"
                                        type="submit" name="${articleTitle}" onclick="javascript:location.href='/user/edit/${articleTitle}'">編輯</button>
                                            <button
                                         class="btn btn-sm btn-pk03 border-2 border-gr0200 rounded-pill text-gr0200 fw-bold"
                                        type="submit" name="${articleTitle}" onclick="javascript:location.href='/user/delete/${articleTitle}/${articleId}'">刪除</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        `;}
})