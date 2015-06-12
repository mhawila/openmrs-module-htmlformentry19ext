package org.openmrs.module.htmlformentry19ext;

import org.openmrs.Provider;
import org.openmrs.api.context.Context;
import org.openmrs.module.htmlformentry.FormEntryContext;
import org.openmrs.module.htmlformentry19ext.element.ProviderStub;
import org.openmrs.module.htmlformentry.widget.Widget;
import org.openmrs.module.htmlformentry.widget.WidgetFactory;
import org.openmrs.module.htmlformentry19ext.util.MatchMode;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class ProviderAjaxAutoCompleteWidget implements Widget {

    private MatchMode matchMode = MatchMode.START;

    private Provider initialValue;
    private final String SRC = "providers";

    public MatchMode getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(MatchMode matchMode) {
<<<<<<< HEAD
        if(matchMode == null) this.matchMode = MatchMode.ANYWHERE;
        else this.matchMode = matchMode;
=======
        if(matchMode != null) this.matchMode = matchMode;
>>>>>>> 9b9194e... HTML-384: Added support for ajax autocomplete provider to EncounterProviderAndRole tag. To accomplish this I have added support for two attributes.
    }

    @Override
    public void setInitialValue(Object initialValue) {
         this.initialValue = (Provider)initialValue;
    }

    @Override
    public String generateHtml(FormEntryContext context) {

        StringBuilder sb = new StringBuilder();
        if (context.getMode().equals(FormEntryContext.Mode.VIEW)) {
            String toPrint = "";
            if (initialValue != null) {
                toPrint = initialValue.getName();
                return WidgetFactory.displayValue(toPrint);
            } else {
                return WidgetFactory.displayEmptyValue("___________");
            }
        } else {
            String placeholder = Context.getMessageSourceService().getMessage("htmlformentry19ext.providerPlaceHolder");
            sb.append("<input type=\"text\"  id=\""
                    + context.getFieldName(this) + "\"" + " name=\""
                    + context.getFieldName(this) + "\" "
                    + " onfocus=\"setupProviderAutocomplete(this, '" + this.SRC + "');\""
                    + " class=\"autoCompleteText\""
                    + " onchange=\"setValWhenAutocompleteFieldBlanked(this)\""
                    + " onblur=\"onBlurAutocomplete(this)\""
                    + " placeholder = \"" + placeholder + "\"");

            if (initialValue != null)
                sb.append(" value=\"" + (new ProviderStub(initialValue).getDisplayValue()) + "\"");
            sb.append("/>\n");

            //Add hidden field for provider match mode
<<<<<<< HEAD
            sb.append("<input type='hidden' id='").append(context.getFieldName(this)+"_matchMode_hid'").
                    append(" value='").append(matchMode).append("' />\n");
=======
            sb.append("<input type=\"hidden\" id=\"").append(context.getFieldName(this)+"_matchMode_hid\" class=\"autoCompleteHidded\"").
                    append(" value=\"").append(matchMode).append("\" />\n");
>>>>>>> 9b9194e... HTML-384: Added support for ajax autocomplete provider to EncounterProviderAndRole tag. To accomplish this I have added support for two attributes.

            sb.append("<input name=\"" + context.getFieldName(this) + "_hid"
                    + "\" id=\"" + context.getFieldName(this) + "_hid" + "\""
                    + " type=\"hidden\" class=\"autoCompleteHidden\" ");
            if (initialValue != null) {
                sb.append(" value=\"" + initialValue.getProviderId() + "\"");
            }
            sb.append("/> \n");
        }
        sb.append(createJsAutoCompleteFunction());

        return sb.toString();
    }

    @Override
    public Object getValue(FormEntryContext context, HttpServletRequest request) {
        String value = request.getParameter(context.getFieldName(this)+"_hid");
        Provider provider = null;
        if(StringUtils.hasText(value)) {
            provider = Context.getProviderService().getProvider(Integer.valueOf(value));
        }
        return provider;
    }

    private String createJsAutoCompleteFunction() {
        StringBuilder sb = new StringBuilder("<script type=\"text/javascript\">\n");
        sb.append("function setupProviderAutocomplete(element,src) {");
        sb.append("    var hiddenField = jQuery(\"#\"+element.id+\"_hid\");\n");
        sb.append("    var textField = jQuery(element); \n");
        sb.append("    var providerMatchMode = jQuery(\"#\"+element.id+\"_matchMode_hid\"); \n");
<<<<<<< HEAD
        sb.append("    var select = false;");

        sb.append("    var req = { 'searchParam': textField.val()};\n");
=======
        sb.append("    var select = false; \n");
>>>>>>> 9b9194e... HTML-384: Added support for ajax autocomplete provider to EncounterProviderAndRole tag. To accomplish this I have added support for two attributes.

        sb.append("    if (hiddenField.length > 0 && textField.length > 0) { \n");
        sb.append("        textField.autocomplete( { \n");
        sb.append("          source: function(req, add){ \n");
        sb.append("            //pass request to server  \n");

        sb.append("            jQuery.getJSON(location.protocol + '//' + location.host + getContextPath() + " +
                "'/ws/module/htmlformentry19ext/' + src ," +
<<<<<<< HEAD
                "{\"searchParam\":textField.val(), 'matchMode':providerMatchMode.val()}, function(data) { \n");

        sb.append("                //create array for response objects \n");
        sb.append("                var suggestions = [];");
        sb.append("                jQuery.each(data,function(i,val){ \n");
        sb.append("                    var item = {}; ");
        sb.append("                    item.label = val.displayValue; \n");
        sb.append("                   item.value = val.providerId; \n");
=======
                "{\"searchParam\":textField.val(), \"matchMode\":providerMatchMode.val()}, function(data) { \n");

        sb.append("                //create array for response objects \n");
        sb.append("                var suggestions = []; \n");
        sb.append("                jQuery.each(data,function(i,val){ \n");
        sb.append("                    var item = {}; \n");
        sb.append("                    item.label = val.displayValue; \n");
        sb.append("                    item.value = val.providerId; \n");
>>>>>>> 9b9194e... HTML-384: Added support for ajax autocomplete provider to EncounterProviderAndRole tag. To accomplish this I have added support for two attributes.
        sb.append("                    suggestions.push(item);  \n");
        sb.append("                }); \n");

        sb.append("\n");
        sb.append("                if (suggestions.length==0) { \n");
        sb.append("                     hiddenField.val(''); \n");
        sb.append("                     textField.css('color','red'); \n");
        sb.append("                } \n");
        sb.append("                add(suggestions);  \n");
        sb.append("            }); \n");
        sb.append("        }, \n");

        sb.append("        minLength: 2, \n");
        sb.append("        select: function(event, ui) {\n");
        sb.append("            hiddenField.val(ui.item.value); \n");
        sb.append("            textField.val(ui.item.label); \n");
        sb.append("            textField.css('color','black') \n");
        sb.append("            select = true; \n");
        sb.append("            return false; \n");
<<<<<<< HEAD
        sb.append("        }  \n");
        sb.append("      });  \n");
=======
        sb.append("         },  \n");
        sb.append("         close: function(event, ui) { \n");
        sb.append("             if(!select){  \n");
        sb.append("                 textField.css('color', 'red'); \n");
        sb.append("                 hiddenField.val(\"\");\n");
        sb.append("             } else {\n");
        sb.append("                 textField.css('color','black'); \n");
        sb.append("             } \n");
        sb.append("             select = false; \n");
        sb.append("         } \n");
        sb.append("      })  \n");
        sb.append("  .data('ui-autocomplete')._renderItem = function(ul, item) {\n");
        sb.append("    return $j('<li>').data('autocomplete-item', item).append('<a>'+item.label+'</a>').appendTo(ul);\n");
>>>>>>> 9b9194e... HTML-384: Added support for ajax autocomplete provider to EncounterProviderAndRole tag. To accomplish this I have added support for two attributes.
        sb.append("    }   \n");
        sb.append("} \n");
        sb.append("</script>\n");

        return sb.toString();
    }
}
