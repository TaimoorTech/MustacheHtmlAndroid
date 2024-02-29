package com.example.liquidtemplateapplication;

public class template {
    static String liquidTemplate="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "  <head>\n" +
            "    <meta charset=\"UTF-8\" />\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
            "    <title>{{ title }}</title>\n" +
            "    <style>\n" +
            "      body {\n" +
            "        display: flex;\n" +
            "        align-items: center;\n" +
            "        justify-content: space-between;\n" +
            "        height: 100vh;\n" +
            "        margin: 0;\n" +
            "        padding: 20px;\n" +
            "      }\n" +
            "\n" +
            "      #left-section {\n" +
            "        flex: 1;\n" +
            "        text-align: center;\n" +
            "      }\n" +
            "\n" +
            "      #right-section {\n" +
            "        flex: 1;\n" +
            "        text-align: center;\n" +
            "      }\n" +
            "\n" +
            "      table {\n" +
            "        width: 100%;\n" +
            "        border-collapse: collapse;\n" +
            "        margin-top: 20px;\n" +
            "      }\n" +
            "\n" +
            "      th,\n" +
            "      td {\n" +
            "        border: 1px solid #1d1a1a;\n" +
            "        padding: 8px;\n" +
            "        text-align: left;\n" +
            "      }\n" +
            "\n" +
            "      th {\n" +
            "        background-color: #ddd;\n" +
            "      }\n" +
            "      .equipment-details {\n" +
            "        border: 1px solid #ddd;\n" +
            "        padding: 10px;\n" +
            "        box-sizing: border-box;\n" +
            "        text-align: left;\n" +
            "        display: flex;\n" +
            "        flex-direction: column;\n" +
            "      }\n" +
            "\n" +
            "      label {\n" +
            "        margin-bottom: 8px;\n" +
            "      }\n" +
            "      .label-header-div {\n" +
            "        margin-top: 10px;\n" +
            "        background-color: #ddd;\n" +
            "      }\n" +
            "      .label-header {\n" +
            "        border: 1px solid #ddd;\n" +
            "        padding: 10px;\n" +
            "        box-sizing: border-box;\n" +
            "        text-align: left;\n" +
            "        display: flex;\n" +
            "        flex-direction: column;\n" +
            "      }\n" +
            "      .label-body {\n" +
            "      }\n" +
            "      .label-Footer {\n" +
            "      }\n" +
            "      .table_main_div {\n" +
            "        width: 95%;\n" +
            "        padding-left: 5%;\n" +
            "      }\n" +
            "      .container {\n" +
            "        display: flex;\n" +
            "      }\n" +
            "\n" +
            "      .left-div {\n" +
            "        padding: 10px;\n" +
            "      }\n" +
            "      .right-div {\n" +
            "        padding: 10px;\n" +
            "      }\n" +
            "\n" +
            "      .left-div img {\n" +
            "        max-width: 90%;\n" +
            "        height: auto;\n" +
            "      }\n" +
            "      .fontSize12 {\n" +
            "        font-size: 12px;\n" +
            "      }\n" +
            "      .backcolor {\n" +
            "        background-color: #ddd;\n" +
            "      }\n" +
            "      .imgSize {\n" +
            "        width: 160px;\n" +
            "        height: 50px;\n" +
            "      }\n" +
            "      .fontweight {\n" +
            "        font-weight: bold;\n" +
            "        font-size: 16px;\n" +
            "      }\n" +
            "    </style>\n" +
            "  </head>\n" +
            "\n" +
            "  <body>\n" +
            "    <div id=\"right-section\">\n" +
            "      <div class=\"container\">\n" +
            "        <div class=\"left-div\">\n" +
            "          <img\n" +
            "            class=\"imgSize\"\n" +
            "            src={{ im0age }}\n" +
            "            alt=\"Description of the image\"\n" +
            "          />\n" +
            "        </div>\n" +
            "        <div class=\"right-div fontweight\">\n" +
            "          <h2>{{name}}</h2>\n" +
            "          <div>\n" +
            "            <label>As of&nbsp;&nbsp;{{ date }} </label>\n" +
            "          </div>\n" +
            "        </div>\n" +
            "      </div>\n" +
            "\n" +
            "      <div>\n" +
            "        <table>\n" +
            "          <thead>\n" +
            "            <tr>\n" +
            "              <th class=\"fontSize12\">Equipment Category: {{ category }}</th>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "              <th class=\"fontSize12\">Manufacturer: {{ manufacturer }}</th>\n" +
            "            </tr>\n" +
            "          </thead>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "      <div>\n" +
            "        <div>\n" +
            "          <table>\n" +
            "            <thead>\n" +
            "              <tr>\n" +
            "                <th class=\"fontSize12\">Country: : {{ country }}</th>\n" +
            "              </tr>\n" +
            "              <tr>\n" +
            "                <th class=\"fontSize12\">Project</th>\n" +
            "                <th class=\"fontSize12\">Actual Amount</th>\n" +
            "                <th class=\"fontSize12\">Warehoused</th>\n" +
            "                <th class=\"fontSize12\">Installed</th>\n" +
            "              </tr>\n" +
            "            </thead>\n" +
            "            <tbody>\n" +
            "              <tr>\n" +
            "                <td class=\"fontSize12\">{{ country }} Project</td>\n" +
            "                <td class=\"fontSize12\">{{ amounts1 }}</td>\n" +
            "                <td class=\"fontSize12\">{{ amounts2 }}</td>\n" +
            "                <td class=\"fontSize12\">{{ amounts3 }}</td>\n" +
            "              </tr>\n" +
            "              <tr class=\"backcolor\">\n" +
            "                <td class=\"fontSize12\">Total in {{ country }}</td>\n" +
            "                <td class=\"fontSize12\">{{ amounts1 }}</td>\n" +
            "                <td class=\"fontSize12\">{{ amounts2 }}</td>\n" +
            "                <td class=\"fontSize12\">{{ amounts3 }}</td>\n" +
            "              </tr>\n" +
            "            </tbody>\n" +
            "          </table>\n" +
            "        </div>\n" +
            "      </div>\n" +
            "      <div>\n" +
            "        <table>\n" +
            "          <thead>\n" +
            "            <tr>\n" +
            "              <th class=\"fontSize12\">Country: {{ country }}</th>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "              <th class=\"fontSize12\">Project</th>\n" +
            "              <th class=\"fontSize12\">Actual Amount</th>\n" +
            "              <th class=\"fontSize12\">Warehoused</th>\n" +
            "              <th class=\"fontSize12\">Installed</th>\n" +
            "            </tr>\n" +
            "          </thead>\n" +
            "          <tbody>\n" +
            "            <tr>\n" +
            "              <td class=\"fontSize12\">{{ country }} Project</td>\n" +
            "              <td class=\"fontSize12\">{{ amounts1 }}</td>\n" +
            "              <td class=\"fontSize12\">{{ amounts2 }}</td>\n" +
            "              <td class=\"fontSize12\">{{ amounts3 }}</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"backcolor\">\n" +
            "              <td class=\"fontSize12\">Total in {{ country }}</td>\n" +
            "              <td class=\"fontSize12\">{{ amounts1 }}</td>\n" +
            "              <td class=\"fontSize12\">{{ amounts2 }}</td>\n" +
            "              <td class=\"fontSize12\">{{ amounts3 }}</td>\n" +
            "            </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </body>\n" +
            "</html>\n";
}