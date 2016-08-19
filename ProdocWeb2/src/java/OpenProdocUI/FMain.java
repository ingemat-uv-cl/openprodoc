/*
 * OpenProdoc
 * 
 * See the help doc files distributed with
 * this work for additional information regarding copyright ownership.
 * Joaquin Hierro licenses this file to You under:
 * 
 * License GNU GPL v3 http://www.gnu.org/licenses/gpl.html
 * 
 * you may not use this file except in compliance with the License.  
 * Unless agreed to in writing, software is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * author: Joaquin Hierro      2016
 * 
 */
package OpenProdocUI;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Joaquin
 */
public class FMain 
{
private static final String Html="<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>OpenProdoc2 Web</title>\n" +
"        <script src=\"js/OpenProdoc.js\" type=\"text/javascript\"></script>\n" +
"        <script src=\"js/dhtmlx.js\" type=\"text/javascript\"></script>\n" +
"        <link rel=\"STYLESHEET\" type=\"text/css\" href=\"js/dhtmlx.css\">\n" +
"        <link rel=\"STYLESHEET\" type=\"text/css\" href=\"css/OpenProdoc.css\">\n" +
"        <style>\n" +
"        html, body {\n" +
"                width: 100%;\n" +
"                height: 100%;\n" +
"                margin: 0px;\n" +
"                padding: 0px;\n" +
"                overflow: hidden;\n" +
"           }\n" +
"	</style>\n" +
"        </head>\n" +
"    <body onload=\"doOnLoadMain();\" >\n" +
"    <div id=\"MainBody\"></div>\n" +
"    </body>\n" +
"</html>";
//-------------------------------------------------------------------
public FMain(HttpSession Sess)
{
    
}
//-------------------------------------------------------------------
public String toHtml()
{
return(Html);    
}
//-------------------------------------------------------------------
}