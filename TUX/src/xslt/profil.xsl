<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:dico="http://myGame/tux"
                version="1.0">
    <xsl:output method="html"/>

    <!-- template for the profil document -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Profil</title>
                <link rel="stylesheet" type="text/css" href="style.css"/>
            </head>
            <body>
                <h1>Profil du joueur</h1>
                <div class="avatar">
                    <img src="{dico:avatar}" alt="Avatar"/>
                </div>
                <div class="info">
                    <p>Nom : <xsl:value-of select="dico:nom"/></p>
                    <p>Anniversaire : <xsl:value-of select="dico:anniversaire"/></p>
                </div>
                <h2>Parties</h2>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Temps</th>
                        <th>Mot</th>
                        <th>Trouvé</th>
                    </tr>
                    <xsl:apply-templates select="dico:parties/dico:partie"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <!-- template for each partie -->
    <xsl:template match="dico:partie">
        <tr>
            <td><xsl:value-of select="@date"/></td>
            <td><xsl:value-of select="dico:temps"/></td>
            <td><xsl:value-of select="dico:mot"/></td>
            <td><xsl:value-of select="@trouvé"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>

