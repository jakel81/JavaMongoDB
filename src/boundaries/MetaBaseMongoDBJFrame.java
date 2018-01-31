/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundaries;

import com.mongodb.*;
import com.mongodb.client.*;
import java.awt.Component;
import java.util.*;
import javax.swing.*;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Jo
 */
public class MetaBaseMongoDBJFrame extends javax.swing.JFrame {

    private DefaultListModel idtm1;
    private DefaultListModel idtm2;
    private DefaultListModel idtm3;
    private DefaultListModel idtm4;
    private String lsSelectedDB;
    private String lsSelectedCollection;
    private String lsSelectedDocument;

    /**
     * Creates new form MetaBaseMongoDBJFrame
     */
    public MetaBaseMongoDBJFrame() {
        initComponents();

        setTitle("Metabase MongoDB");
        setVisible(true);

        remplirJListeDatabases();
    }

    private void clear() {
        try {
            Component[] tComposants = getContentPane().getComponents();
            for (int i = 0; i < tComposants.length; i++) {
                Object objet = tComposants[i];
                if (objet.getClass().toString().equals("class javax.swing.JLabel")) {
                    JLabel composant = (JLabel) objet;
                    composant.setLabelFor(jLabelMessage);
                }
            }

        } catch (Exception e) {
            jLabelMessage.setText(e.getMessage());
        }
    }

    private void remplirJListeDatabases() {

        try {

            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoIterable<String> listeDB = mongoClient.listDatabaseNames();

            idtm1 = new DefaultListModel<>();

            for (String lsDB : listeDB) {
                System.out.println(lsDB);
                idtm1.addElement(lsDB);
                jListDatabases.setModel(idtm1);
            }

        } catch (Exception e) {

        }

        jLabelMessage.setText("Chargement databases réussi!!");
        clear();

    }

    private void remplirJListeCollections(String psNomDb) {

        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoDatabase db = mongoClient.getDatabase(psNomDb);
            MongoIterable<String> collections = db.listCollectionNames();

            idtm2 = new DefaultListModel<>();

            for (String lsColl : collections) {
                System.out.println(lsColl);
                idtm2.addElement(lsColl);
                jListCollections.setModel(idtm2);
            }
        } catch (Exception e) {

        }

        jLabelMessage.setText("Chargement collections réussi!!");
        clear();

    }

    private void remplirJListeDocuments(String psNomDb, String psNomCollection) {

        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoDatabase db = mongoClient.getDatabase(psNomDb);
            MongoCollection collections = db.getCollection(psNomCollection);
            FindIterable<Document> find = collections.find();
            MongoCursor<Document> cursor = find.iterator();

            idtm3 = new DefaultListModel<>();

            while (cursor.hasNext()) {
                Document document = (Document) cursor.next();
                System.out.println(cursor.next().toString());
                Set<Map.Entry<String, Object>> champs = document.entrySet();

                for (Map.Entry<String, Object> entry : champs) {
                    if (entry.getKey().equals("_id")) {
                        System.out.print(entry.getValue());
                        idtm3.addElement(entry.getValue().toString());
                        jListDocuments.setModel(idtm3);

                    }
                }

            }

        } catch (Exception e) {

        }

        jLabelMessage.setText("Chargement documents réussi!!");
        clear();

    }

    private void remplirJListeKeys(String psNomDb, String psNomCollection, String psDocument) {
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoDatabase db = mongoClient.getDatabase(psNomDb);
            MongoCollection collections = db.getCollection(psNomCollection);
            Document critere = new Document("_id", new ObjectId(psDocument));
            FindIterable<Document> cursor = collections.find(critere);
            Document doc = cursor.first();
            Set<String> cles = doc.keySet();

            idtm4 = new DefaultListModel();

            for (String keys : cles) {
                System.out.println(keys + ":" + doc.get(keys) + "\n");
                idtm4.addElement(keys + ":" + doc.get(keys) + "\n");
                jListKeys.setModel(idtm4);
            }
        } catch (Exception e) {

        }
        
        jLabelMessage.setText("Chargement clés réussi!!");
        clear();

    }

    private void dropDb(String psNomDb) {

        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoDatabase database = mongoClient.getDatabase(psNomDb);
            database.drop();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDatabases = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListDatabases = new javax.swing.JList<>();
        jLabelCollections = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListCollections = new javax.swing.JList<>();
        jLabelDocuments = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListDocuments = new javax.swing.JList<>();
        jLabelKeys = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListKeys = new javax.swing.JList<>();
        jButtonDropDb = new javax.swing.JButton();
        jButtonDropCollection = new javax.swing.JButton();
        jButtonDropDocument = new javax.swing.JButton();
        jLabelMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Metabase MongoDB");
        setUndecorated(true);

        jLabelDatabases.setText("Databases");

        jListDatabases.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListDatabases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListDatabasesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListDatabases);

        jLabelCollections.setText("Collections");

        jListCollections.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListCollections.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListCollectionsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListCollections);

        jLabelDocuments.setText("Documents");

        jListDocuments.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListDocuments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListDocumentsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListDocuments);

        jLabelKeys.setText("Keys");

        jListKeys.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jListKeys);

        jButtonDropDb.setText("Drop DB");
        jButtonDropDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDropDbActionPerformed(evt);
            }
        });

        jButtonDropCollection.setText("Drop Collection");
        jButtonDropCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDropCollectionActionPerformed(evt);
            }
        });

        jButtonDropDocument.setText("Drop Document");
        jButtonDropDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDropDocumentActionPerformed(evt);
            }
        });

        jLabelMessage.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDatabases)
                            .addComponent(jButtonDropDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCollections)
                            .addComponent(jButtonDropCollection, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelDocuments))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelKeys)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButtonDropDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelDatabases)
                                .addComponent(jLabelCollections)))
                        .addComponent(jLabelDocuments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelKeys, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDropDb)
                    .addComponent(jButtonDropCollection)
                    .addComponent(jButtonDropDocument))
                .addGap(20, 20, 20)
                .addComponent(jLabelMessage))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDropDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDropDbActionPerformed
        // TODO add your handling code here:
        if (lsSelectedDB == null) {
            jLabelMessage.setText("Veuillez choisir une database !");
        } else {
            dropDb(lsSelectedDB);
            remplirJListeDatabases();
            clear();
            jLabelMessage.setText("Database supprimée !");
        }
    }//GEN-LAST:event_jButtonDropDbActionPerformed

    private void jButtonDropCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDropCollectionActionPerformed
        // TODO add your handling code here:
        if (lsSelectedCollection == null) {
            jLabelMessage.setText("Veuillez choisir une collection !");
        } else {
            //lsSelectedCollection.drop();
            jLabelMessage.setText("Collection supprimée !");
            remplirJListeCollections(lsSelectedDB);
            clear();
        }
    }//GEN-LAST:event_jButtonDropCollectionActionPerformed

    private void jButtonDropDocumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDropDocumentActionPerformed
        // TODO add your handling code here:
        if (lsSelectedDocument == null) {
            jLabelMessage.setText("Veuillez choisir un document");
        } else {
            jLabelMessage.setText("Document supprimée");
            remplirJListeDocuments(lsSelectedDB, lsSelectedCollection);
            clear();
        }
    }//GEN-LAST:event_jButtonDropDocumentActionPerformed

    private void jListDatabasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListDatabasesMouseClicked
        // TODO add your handling code here:        
        lsSelectedDB = jListDatabases.getSelectedValue();
        remplirJListeCollections(lsSelectedDB);
        jLabelMessage.setText(lsSelectedDB);
        idtm3.removeAllElements();
        idtm4.removeAllElements();
    }//GEN-LAST:event_jListDatabasesMouseClicked

    private void jListCollectionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListCollectionsMouseClicked
        // TODO add your handling code here:        
        String lsSelectedDB = jListDatabases.getSelectedValue();
        String lsSelectedCollection = jListCollections.getSelectedValue();
        remplirJListeDocuments(lsSelectedDB, lsSelectedCollection);
        jLabelMessage.setText(lsSelectedCollection);
        idtm4.removeAllElements();
    }//GEN-LAST:event_jListCollectionsMouseClicked

    private void jListDocumentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListDocumentsMouseClicked
        // TODO add your handling code here:
        String lsSelectedDB = jListDatabases.getSelectedValue();
        String lsSelectedCollection = jListCollections.getSelectedValue();
        String lsSelectedDocument = jListDocuments.getSelectedValue();
        remplirJListeKeys(lsSelectedDB, lsSelectedCollection, lsSelectedDocument);
        jLabelMessage.setText(lsSelectedDocument);
    }//GEN-LAST:event_jListDocumentsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MetaBaseMongoDBJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MetaBaseMongoDBJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MetaBaseMongoDBJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MetaBaseMongoDBJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MetaBaseMongoDBJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDropCollection;
    private javax.swing.JButton jButtonDropDb;
    private javax.swing.JButton jButtonDropDocument;
    private javax.swing.JLabel jLabelCollections;
    private javax.swing.JLabel jLabelDatabases;
    private javax.swing.JLabel jLabelDocuments;
    private javax.swing.JLabel jLabelKeys;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JList<String> jListCollections;
    private javax.swing.JList<String> jListDatabases;
    private javax.swing.JList<String> jListDocuments;
    private javax.swing.JList<String> jListKeys;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
