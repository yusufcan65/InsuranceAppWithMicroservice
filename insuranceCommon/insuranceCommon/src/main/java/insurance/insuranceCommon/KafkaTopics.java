package insurance.insuranceCommon;

public class KafkaTopics {
    public static final String PAYMENT_COMPLETED = "payment-completed";
    public static final String POLICY_CREATED = "policy-created";
    public static final String POLICY_UPDATED = "policy-updated";
    public static final String POLICY_DELETED = "policy-deleted";
    public static final String POLICY_ACTIVATED = "policy-activated";
    public static final String PAYMENT_FAILED = "payment-failed";


    public static final String PAYMENT_COMPLETED_DLT = PAYMENT_COMPLETED + ".DLT";
    public static final String POLICY_CREATED_DLT = POLICY_CREATED + ".DLT";
    public static final String POLICY_UPDATED_DLT = POLICY_UPDATED + ".DLT";
    public static final String POLICY_DELETED_DLT = POLICY_DELETED + ".DLT";
    public static final String POLICY_ACTIVATED_DLT = POLICY_ACTIVATED + ".DLT";
    public static final String PAYMENT_FAILED_DLT = PAYMENT_FAILED + ".DLT";
}