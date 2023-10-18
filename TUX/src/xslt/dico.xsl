<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dico.xsl
    Created on : 17 octobre 2023, 16:28
    Author     : belguitr
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:dico= "http://myGame/tux" version="1.0">
    
    
    <xsl:output method="html"/>

    
    
    
    <!-- template for the dico document-->
    <xsl:template match="/">
        <html>
            <head>
                <title>dico.xsl</title>
            </head>
            <body>
            <table>
                    <tr>
                        <td>
                            Mot
                        </td>
                        <td>
                            Niveau
                        </td>
                    </tr>
                   

                    <xsl:apply-templates select="//dico:mot">
                        <xsl:sort select="@niveau" order="ascending"/>
                        <xsl:sort select="text()" order="descending"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>
    
    
    <!-- template for each Mot 
    va afficher : 
    - un mot
    - un niveau
    -->
<xsl:template match="dico:mot">
        <tr>
            <td>
                <xsl:value-of select="text()"/>
            </td>
            <td>
                <xsl:value-of select="@niveau"/>
            </td>
        </tr>
    </xsl:template>

</xsl:stylesheet>
