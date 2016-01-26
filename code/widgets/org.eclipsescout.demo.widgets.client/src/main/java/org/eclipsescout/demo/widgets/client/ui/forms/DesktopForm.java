package org.eclipsescout.demo.widgets.client.ui.forms;

import org.eclipse.scout.rt.client.ui.desktop.notification.DesktopNotification;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.status.Status;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipsescout.demo.widgets.client.ClientSession;
import org.eclipsescout.demo.widgets.client.ui.desktop.outlines.IAdvancedExampleForm;
import org.eclipsescout.demo.widgets.client.ui.forms.DesktopForm.MainBox.CloseButton;
import org.eclipsescout.demo.widgets.client.ui.forms.DesktopForm.MainBox.ExamplesBox.DurationField;
import org.eclipsescout.demo.widgets.client.ui.forms.DesktopForm.MainBox.ExamplesBox.MessageField;
import org.eclipsescout.demo.widgets.client.ui.forms.DesktopForm.MainBox.ExamplesBox.SeverityField;
import org.eclipsescout.demo.widgets.shared.services.code.SeverityCodeType;

@Order(8100.0)
public class DesktopForm extends AbstractForm implements IAdvancedExampleForm {

  public DesktopForm() {
    super();
  }

  @Override
  public void startPageForm() {
    startInternal(new PageFormHandler());
  }

  @Override
  public AbstractCloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public MessageField getMessageField() {
    return getFieldByClass(MessageField.class);
  }

  public SeverityField getSeverityField() {
    return getFieldByClass(SeverityField.class);
  }

  public DurationField getDurationField() {
    return getFieldByClass(DurationField.class);
  }

  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10)
    public class CloseButton extends AbstractCloseButton {
    }

    @Order(20)
    public class ShowNotificationButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ShowNotification");
      }

      @Override
      protected void execClickAction() {
        IStatus status = new Status(getMessageField().getValue(), getSeverityField().getValue());
        long duration = getDurationField().getValue();
        DesktopNotification notification = new DesktopNotification(status, duration);
        ClientSession.get().getDesktop().addNotification(notification);
      }
    }

    @Order(30)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 2;
      }

      @Order(10)
      public class MessageField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Message");
        }

        @Override
        protected void execInitField() {
          setValue("Foo Bar");
        }
      }

      @Order(40)
      public class SeverityField extends AbstractSmartField<Integer> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Severity");
        }

        @Override
        protected Class<? extends ICodeType<?, Integer>> getConfiguredCodeType() {
          return SeverityCodeType.class;
        }

        @Override
        protected void execInitField() {
          setValue(IStatus.INFO);
        }
      }

      @Order(50)
      public class DurationField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Duration");
        }

        @Override
        protected void execInitField() {
          setValue(5000);
        }
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }

}