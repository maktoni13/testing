<%@include file="jspf/headererror.jspf"%>
<br/>
<div class="dropdown-menu" aria-labelledby="navbarDropdownLang">
    <form action = "language" method="post" >
        <input type="hidden" name="command" value="changelocale">
        <select id="language" name="language" onchange="submit()">
            <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>EN</option>
            <option value="ua_UA" ${language == 'ua_UA' ? 'selected' : ''}>UA</option>
            <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>RU</option>
        </select>
    </form>
</div>

        <h2>
           Error Page<br/>
            <i>Error <%= exception %></i>
        </h2>


       <br>

<%@include file="jspf/footer.jspf"%>