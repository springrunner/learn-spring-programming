export default {
    showResponse(type, text, element) {
        let responseElementId = element.attr("id") + "Response"
        let responseElement = $("#" + responseElementId)
        if (responseElement.length === 0) {
            responseElement = $('<span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>').insertAfter(element)
        } else {
            responseElement.replaceWith('<span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>')
            responseElement = $("#" + responseElementId)
        }
        responseElement.fadeIn("slow")
    },
    showSuccessResponse(text, element) {
        this.showResponse("success", text, element)
    },
    showErrorResponse(text, element) {
        this.showResponse("error", text, element)
    },
    xmlencode(xml) {
        let text;
        if (window.ActiveXObject) {
            // for IE
            text = xml.xml;
        } else {
            // for Mozilla, Firefox, Opera, etc.
            text = (new XMLSerializer()).serializeToString(xml);
        }
        return text.replace(/\&/g,'&'+'amp;').replace(/</g,'&'+'lt;')
                   .replace(/>/g,'&'+'gt;').replace(/\'/g,'&'+'apos;').replace(/\"/g,'&'+'quot;')
    },
    isEmpty(value) {
        return typeof value === 'string' && !value.trim() || typeof value === 'undefined' || value === null
    },
    isNotEmpty(value) {
        return this.isEmpty(value) === false
    }
}