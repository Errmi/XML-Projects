<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : visites.xsl
    Created on : 7. november 2023, 15:23
    Author     : saraengesvik
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:med="http://www.ujf-grenoble.fr/l3miage/medical" 
                xmlns:act="http://www.ujf-grenoble.fr/l3miage/actes"
                version="1.0">
    
    <xsl:output method="html"/>
    

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    <!--Parametre global de l'infirmier dequelle on va afficher la page.-->
    <xsl:param name="destinedId" select="001"/>
    
    <!--Variable qui contient l'ensemble des noeuds visite correspondant à l'infirmière donnée-->
    <xsl:variable name="visitesDuJour" select="//med:visite[@intervenant=$destinedId]"/>
    
    <!--Variable pour piocher des informations des actes dans document XML "actes.xml"-->
    <xsl:variable name="actes" select="document('actes.xml', /)/act:ngap"/>
    
    <!--Code javascript avec un methode qui affiche une fenêtre 
        (pour l'instant vide) en cliquant sur le bouton Facture. -->
    <script type="text/javascript">
        <![CDATA[
            function openFacture(prenom, nom, actes) {
                var width  = 500;
                var height = 300;
                if(window.innerWidth) {
                    var left = (window.innerWidth-width)/2;
                    var top = (window.innerHeight-height)/2;
                }
                else {
                    var left = (document.body.clientWidth-width)/2;
                    var top = (document.body.clientHeight-height)/2;
                }
                var factureWindow = window.open('','facture','menubar=yes, scrollbars=yes, top='+top+', left='+left+', width='+width+', height='+height+'');
                factureText = "Facture pour : " + prenom + " " + nom;
                factureWindow.document.write(factureText);
             }  
        ]]>
    </script>
    
    <!--Traverser le document de la racine: 
        1. Afficher le prénom de l'infirmière étant donné son identifiant 
           et le nombre de patients pour cette infirmière    
        2. Apply templates pour lister chaque patient que l'infirmier actuelle 
           va visiter (dans l'ordre de visite) et ajouter un bouton "Facture" pour
           chaque patient. -->
    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" href="visites.css"/>
                <title>visites.xsl</title>
            </head>
            <body>
                Bonjour <xsl:value-of select="//med:infirmier[@id=$destinedId]/med:prénom/text()"/>,<br/><br/>
                Aujourd'hui, vous avez <xsl:value-of select="count($visitesDuJour)"/> patients<br/><br/>
                
                Visites:
                <table>
                    <xsl:apply-templates select="$visitesDuJour/parent::med:patient">
                        <xsl:sort select="@date" order="ascending"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>
    
    <!--Template for :    
    1. Affiche nom et adresse du patient que l'infirmier va visiter
    2. Affiche la liste des soins à effectuer: Le date de visite, et apply un
       autre template qui affiche chaque acte (numéro et text)
    3. Ajouter un bouton "Facture" qui ouvrira une nouvelle fenêtre avec la 
       facture correspondante pour le patient. Un attribut qui appelle 
       le méthode (javascript) "openFacture" pour qu'un fenêtre s'affiche en 
       cliquant le bouton. -->
    <xsl:template match="med:patient">
        <tr>
            <td>
                <xsl:value-of select="med:nom/text()"/>, 
                <xsl:value-of select="med:prénom/text()"/><br/><br/>
                <xsl:value-of select="med:adresse/med:numéro"/><xsl:text> </xsl:text>
                <xsl:value-of select="med:adresse/med:rue"/>,<br/>
                <xsl:value-of select="med:adresse/med:codePostal"/><xsl:text> </xsl:text>
                <xsl:value-of select="med:adresse/med:ville"/>
            </td>
            <td>
                <xsl:value-of select="med:visite/@date"/><br/>
                <xsl:apply-templates select="med:visite/med:acte"/>
                
                <!--Variable pour le ID des actes-->
                <xsl:variable name="actesId" select="med:visite/med:acte/@id"/>
                
                <input>
                    <xsl:attribute name="type">button</xsl:attribute>
                    <xsl:attribute name="value">Fracture</xsl:attribute>
                    <xsl:attribute name="onclick">
                    openFacture('<xsl:value-of select="med:prénom/text()"/>', 
                                '<xsl:value-of select="med:nom/text()"/>', 
                                '<xsl:copy-of select="$actes/act:actes/act:acte[@id=$actesId]"/>')
                    </xsl:attribute>
                </input>
            </td>
        </tr>
    </xsl:template>
   
   <!--Template pour afficher le numéro et text (de document XML actes.xml)-->
    <xsl:template match="med:acte">
        <xsl:variable name="acteId" select="@id"/>
        acte 
        <xsl:value-of select="$acteId"/>: 
        <xsl:value-of select="$actes/act:actes/act:acte[@id=$acteId]"/><br/>
    </xsl:template>
   
</xsl:stylesheet>
