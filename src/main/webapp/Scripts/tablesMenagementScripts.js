// Funzione per mostrare/nascondere la tabella
function toggleTable() {
    // Recupera l'elemento della tendina
    var tableDiv = document.getElementById("tableDiv");

    // Cambia la visibilità dell'elemento (mostra/nascondi)
    if (tableDiv.style.display === "none") {
        tableDiv.style.display = "block";
    } else {
        tableDiv.style.display = "none";
    }
}

// Funzione per esportare la tabella in diversi formati
function exportTable(format) {
    var table = document.querySelector('table'); // Seleziona la tabella
    var tableData = []; // Array per i dati della tabella

    // Cicla attraverso le righe della tabella
    for (var i = 1; i < table.rows.length; i++) {
        var row = table.rows[i];
        var rowData = [];

        // Cicla attraverso le colonne della riga
        for (var j = 0; j < row.cells.length; j++) {
            rowData.push(row.cells[j].textContent);
        }

        tableData.push(rowData); // Aggiungi dati della riga all'array
    }

    if (format === 'pdf') {
        var printContents = document.getElementById("tableDiv").innerHTML;
        var originalContents = document.body.innerHTML;

        document.body.innerHTML = printContents;

        window.print();

        document.body.innerHTML = originalContents;
    } else if (format === 'json') {
        var exportData = JSON.stringify(tableData, null, 2);
        downloadData(exportData, 'table_export.json');
    } else if (format === 'xml') {
        var exportData = generateXML(tableData);
        downloadData(exportData, 'table_export.xml');
    }
}

// Funzione per scaricare dati in formato file
function downloadData(data, filename) {
    var blob = new Blob([data], { type: 'application/octet-stream' });
    var link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = filename;
    link.click();
}

// Funzione per generare XML da dati della tabella
function generateXML(tableData) {
    var xml = '<?xml version="1.0" encoding="UTF-8"?>\n<TableData>\n';

    for (var i = 0; i < tableData.length; i++) {
        xml += '  <Row>\n';
        for (var j = 0; j < tableData[i].length; j++) {
            xml += '    <Cell>' + tableData[i][j] + '</Cell>\n';
        }
        xml += '  </Row>\n';
    }

    xml += '</TableData>';
    return xml;
}
