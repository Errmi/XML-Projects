<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : profil.xsl
    Created on : 17 octobre 2023, 18:16
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
                <title>profil.xsl</title>
            </head>
            <body>
                <table>
                    <xsl:apply-templates select="//dico:profil"/>
                </table>

              
            </body>
        </html>
    </xsl:template>
    
    
    <!-- template for each Mot 
    va afficher : 
    - un mot
    - un niveau
    -->
<xsl:template match="dico:profil">
        <tr>
            <td>
                <xsl:value-of select="dico:nom"/>
            </td>
            <td>
                <xsl:value-of select="dico:avatar"/>
            </td>
            <td>
                <xsl:value-of select="dico:anniversaire"/>
            </td>
            


        </tr>
    </xsl:template>

</xsl:stylesheet>
