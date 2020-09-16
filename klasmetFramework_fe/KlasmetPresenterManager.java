/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

import emeruxclient.KlasmetPresenterMain;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import presenter.PresenterHardwareStatistics;
import presenter.PresenterOperationsFm;
import presenter.PresenterServiceStatisticsFM;
import presenter.PresenterSystemStatisticsFM;
import presenter.PresenterServiceStatisticsRA;

/**
 *
 * @author nami
 */
public class KlasmetPresenterManager {

    public static KlasmetPresenterMain klasmetPresenterMain;
    private static final ArrayList<KlasmetPresenterModel> PRESENTER_LIST = new ArrayList<>();

    /**
     * @return the PRESENTER_LIST
     */
    public static ArrayList<KlasmetPresenterModel> getPRESENTER_LIST() {
        return PRESENTER_LIST;
    }

    public static KlasmetPresenterModel getPresenterModelByPresenterClassName(String presenterClassName) {
        for (KlasmetPresenterModel nextKlasmetPresenterModel : PRESENTER_LIST) {
            if (nextKlasmetPresenterModel.getPresenterClassName().equals(presenterClassName)) {
                return nextKlasmetPresenterModel;
            }
        }
        return null;
    }

    public static void reflector(String presenterClassName, String presenterMethodName, String param) {
        switch (presenterClassName) {
            case "KlasmetPresenterSystemClientVersion":
                try {
                    Class<?> cls = Class.forName("klasmetFramework_fe.KlasmetPresenterSystemClientVersion");
                    Object obj = cls.newInstance();
                    Method method = cls.getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(obj, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.KlasmetPresenterSystemClientVersion module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;

            case "KlasmetPresenterSystemStat":
                try {
                    KlasmetPresenterSystemStat presenterSystemStat = (KlasmetPresenterSystemStat) getPresenterModelByPresenterClassName(presenterClassName).getPresenterClassObject();
                    Method method = presenterSystemStat.getClass().getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(presenterSystemStat, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.KlasmetPresenterSystemStat module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;

            case "PresenterHardwareStatistics":
                try {
                    PresenterHardwareStatistics presenterHardwareStatistics = (PresenterHardwareStatistics) getPresenterModelByPresenterClassName(presenterClassName).getPresenterClassObject();
                    Method method = presenterHardwareStatistics.getClass().getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(presenterHardwareStatistics, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.PresenterHardwareStatistics module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;

            case "PresenterServiceStatisticsFM":
                try {
                    PresenterServiceStatisticsFM presenterServiceStatisticsFM = (PresenterServiceStatisticsFM) getPresenterModelByPresenterClassName(presenterClassName).getPresenterClassObject();
                    Method method = presenterServiceStatisticsFM.getClass().getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(presenterServiceStatisticsFM, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.PresenterServiceStatisticsFM module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;

            case "PresenterSystemStatisticsFM":
                try {
                    PresenterSystemStatisticsFM presenterSystemStatisticsFM = (PresenterSystemStatisticsFM) getPresenterModelByPresenterClassName(presenterClassName).getPresenterClassObject();
                    Method method = presenterSystemStatisticsFM.getClass().getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(presenterSystemStatisticsFM, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.PresenterSystemStatisticsFM module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;

            case "PresenterServiceStatisticsRA":
                try {
                    PresenterServiceStatisticsRA presenterServiceStatisticsRA = (PresenterServiceStatisticsRA) getPresenterModelByPresenterClassName(presenterClassName).getPresenterClassObject();
                    Method method = presenterServiceStatisticsRA.getClass().getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(presenterServiceStatisticsRA, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.PresenterServiceStatisticsRA module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;
            case "PresenterReportSparkYesterdaySummery":
                try {
                    Class<?> cls = Class.forName("presenter.PresenterReportSparkYesterdaySummery");
                    Object obj = cls.newInstance();
                    Method method = cls.getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(obj, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.PresenterReportSparkYesterdaySummery module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;

            case "PresenterOperationsFm":
                try {
                    PresenterOperationsFm presenterOperationsFm = (PresenterOperationsFm) getPresenterModelByPresenterClassName(presenterClassName).getPresenterClassObject();
                    Method method = presenterOperationsFm.getClass().getDeclaredMethod(presenterMethodName, String.class);
                    method.invoke(presenterOperationsFm, param);
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("KLASMET Error");
                            alert.setHeaderText(null);
                            alert.setResizable(true);
                            alert.setContentText("Reflection problem in KlasmetPresenterManager.reflector.PresenterOperationsFm module:\n" + ex.toString());
                            alert.showAndWait();
                        }
                    });
                }

                break;

        }

    }

}
