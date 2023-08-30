(ns icd.scroll.letters
  (:require
   [clojure.spec.alpha :as spec]
   [re-frame.core :as re-frame]))

(spec/def ::id int?)

(spec/def ::title string?)

(spec/def ::content string?)

(spec/def ::stack (spec/coll-of ::id :kind vector?)
  #_"A stack of letter ids.")

(spec/def ::top-distance integer?
  #_"The distance in px from the top of the viewport to the top of the element.")

(spec/def ::letter (spec/keys :req [::id
                                    ::title
                                    ::content])
  #_"A medical letter")

(spec/def ::selected-letter ::letter
  #_"The currently selected letter.")

(spec/def ::scroll-positions (spec/map-of ::id ::top-distance))

(re-frame/reg-sub ::selected-letter ::selected-letter)

(re-frame/reg-sub ::scroll-positions ::scroll-positions)

(re-frame/reg-sub ::stack ::stack)

(re-frame/reg-sub ::empty-stack? (fn [db]
                                   (-> db ::stack count (< 2))))

(def letter1
  {::id 1
   ::title "Medical Evaluation and Diagnostic Summary for Sarah Williams"
   ::content
   [:p
    [:span "Ref: 294-8392-ND "]
    [:br]
    [:span "Date: August 29, 2023"]
    [:br]
    [:span "To: Dr. Jane Stevens, M.D."]
    [:br]
    [:span "Department of Internal Medicine"]
    [:br]
    [:span "Community Health Hospital"]
    [:br]
    [:span "123 Health St."]
    [:br]
    [:span "MediTown, ST 12345"]
    [:br]
    [:br]
    [:span "Subject: Medical Evaluation and Diagnostic Summary for Sarah Williams"]
    [:br]
    [:br]
    [:span "Dear Dr. Stevens,"]
    [:br]
    [:span "I am writing to provide a comprehensive overview of Ms. Sarah Williams, a 54-year-old female who recently visited our clinic with multiple complaints. Below are the details of her medical evaluation."]
    [:br]
    [:br]
    [:span "Chief Complaints:"]
    [:br]
    [:span "1. Persistent cough lasting more than 3 weeks"]
    [:br]
    [:span "2. Fatigue"]
    [:br]
    [:span "3. Shortness of breath"]
    [:br]
    [:br]
    [:span "Past Medical History:"]
    [:br]
    [:span "1. Hypertension - " [:span {:id "1"} "ICD-10: I10"]]
    [:br]
    [:span "2. Type 2 Diabetes Mellitus - " [:span {:id "2"} "ICD-10: E11"]]
    [:br]
    [:span "3. Hyperlipidemia - " [:span {:id "3"} "ICD-10: E78.5"]]
    [:br]
    [:br]
    [:span "Diagnostic Assessment:"]
    [:br]
    [:span "After initial physical examination and based on the symptoms presented, a series of diagnostic tests were conducted. The key findings are as follows:"]
    [:br]
    [:span "1. Chest X-ray revealed diffuse nodular patterns."]
    [:br]
    [:span "2. Blood tests indicated elevated levels of C-reactive protein."]
    [:br]
    [:span "3. Spirometry test indicated restricted airflow."]
    [:br]
    [:span "Based on these findings, Ms. Williams was preliminarily diagnosed with Sarcoidosis - " [:span {:id "4"} "ICD-10: D86.0"]]
    [:br]
    [:br]
    [:span "Additional ICD-10 codes for symptoms include:"]
    [:br]
    [:span "1. Chronic cough - " [:span {:id "5"} "ICD-10: R05"]]
    [:br]
    [:span "2. Fatigue - " [:span {:id "6"} "ICD-10: R53"]]
    [:br]
    [:span "3. Shortness of breath - " [:span {:id "7"} "ICD-10: R06.02"]]
    [:br]
    [:br]
    [:span "Possible External Cause:"]
    [:br]
    [:span "Ms. Williams was also questioned about any possible environmental factors that could have contributed to her condition. She revealed that she was exposed to industrial fumes in her neighborhood recently. Hence, external cause of the disease is recorded as - " [:span {:id "8"} "ICD-10: Z77.22"]]
    [:br]
    [:br]
    [:span "Treatment Plan:"]
    [:br]
    [:span "1. Corticosteroids for reducing inflammation."]
    [:br]
    [:span "2. Bronchodilators for easing breathing issues."]
    [:br]
    [:span "3. Routine monitoring of blood sugar and blood pressure due to her existing comorbidities."]
    [:br]
    [:br]
    [:span "Follow-Up:"]
    [:br]
    [:span "Ms. Williams is scheduled for a follow-up visit in two weeks to assess the effectiveness of the treatment and make any necessary adjustments."]
    [:br]
    [:br]
    [:span "Should you need any more details or clarification, please feel free to contact me."]
    [:br]
    [:span "Sincerely,"]
    [:br]
    [:span "Dr. Emily Thompson, M.D."]
    [:br]
    [:span "Specialist in Pulmonary Medicine"]
    [:br]
    [:span "Advanced Medical Clinic"]
    [:br]
    [:span "456 Clinic Way"]
    [:br]
    [:span "MediTown, ST 12346"]
    [:br]
    [:span "Phone: (555) 123-4567"]
    [:br]
    [:span "Email: emily.thompson@medicalclinic.org"]
    [:br]
    [:span "License No: MT293840"]
    [:br]
    [:span "CC: Patient Medical Record - Sarah Williams"]]})

(def letter2
  {::id 2
   ::title "Medical Evaluation and Diagnostic Summary for Brian Johnson"
   ::content
   [:p
    [:span "Ref: 672-1840-JK "]
    [:br]
    [:span "Date: August 29, 2023"]
    [:br]
    [:span "To: Dr. Alan Carter, M.D."]
    [:br]
    [:span "Department of Orthopedics"]
    [:br]
    [:span "Mercy General Hospital"]
    [:br]
    [:span "789 Medical Drive"]
    [:br]
    [:span "HealthCity, ST 67890"]
    [:br]
    [:br]
    [:span "Subject: Medical Evaluation and Diagnostic Summary for Brian Johnson"]
    [:br]
    [:br]
    [:span "Dear Dr. Carter,"]
    [:br]
    [:span "I am writing to provide a summary of the medical evaluation conducted on Mr. Brian Johnson, a 32-year-old male who presented with complaints of persistent lower back pain and radiating leg pain."]
    [:br]
    [:br]
    [:span "Chief Complaints:"]
    [:br]
    [:span "1. Lower back pain lasting over 6 months"]
    [:br]
    [:span "2. Leg pain radiating from the lower back"]
    [:br]
    [:span "3. Limited range of motion"]
    [:br]
    [:br]
    [:span "Past Medical History:"]
    [:br]
    [:span "1. Obesity - " [:span {:id "9"} "ICD-10: E66.9"]]
    [:br]
    [:span "2. Hypothyroidism - " [:span {:id "10"} "ICD-10: E03.9"]]
    [:br]
    [:br]
    [:span "Diagnostic Assessment:"]
    [:br]
    [:span "The following diagnostic tests were performed:"]
    [:br]
    [:span "1. Lumbar MRI indicated a herniated disc at L4-L5."]
    [:br]
    [:span "2. Electromyogram (EMG) showed nerve conduction impairment."]
    [:br]
    [:span "Based on these results, Mr. Johnson was diagnosed with Lumbar Disc Herniation - " [:span {:id "11"} "ICD-10: M51.26"]]
    [:br]
    [:br]
    [:span "Additional ICD-10 codes for symptoms include:"]
    [:br]
    [:span "1. Lower back pain - " [:span {:id "12"} "ICD-10: M54.5"]]
    [:br]
    [:span "2. Sciatica - " [:span {:id "13"} "ICD-10: M54.30"]]
    [:br]
    [:span "3. Limited range of motion - " [:span {:id "14"} "ICD-10: M62.81"]]
    [:br]
    [:br]
    [:span "Possible External Cause:"]
    [:br]
    [:span "Mr. Johnson reported lifting heavy objects frequently at his job. Thus, the external cause of his condition could be related to these activities - " [:span {:id "15"} "ICD-10: W18.30XA"]]
    [:br]
    [:br]
    [:span "Treatment Plan:"]
    [:br]
    [:span "1. Physical therapy focusing on spinal stabilization."]
    [:br]
    [:span "2. Nonsteroidal anti-inflammatory drugs (NSAIDs) for pain management."]
    [:br]
    [:span "3. Weight loss counseling and thyroid hormone therapy adjustment."]
    [:br]
    [:br]
    [:span "Follow-Up:"]
    [:br]
    [:span "Mr. Johnson has been scheduled for a follow-up in four weeks to assess treatment efficacy and decide if surgical intervention is necessary."]
    [:br]
    [:br]
    [:span "Please do not hesitate to contact me for further details or clarifications."]
    [:br]
    [:span "Sincerely,"]
    [:br]
    [:span "Dr. Michael Smith, M.D."]
    [:br]
    [:span "Specialist in Physical Medicine and Rehabilitation"]
    [:br]
    [:span "Green Valley Health Clinic"]
    [:br]
    [:span "234 Wellness Road"]
    [:br]
    [:span "HealthCity, ST 67891"]
    [:br]
    [:span "Phone: (555) 987-6543"]
    [:br]
    [:span "Email: michael.smith@greenvalleyhc.org"]
    [:br]
    [:span "License No: MS472913"]
    [:br]
    [:span "CC: Patient Medical Record - Brian Johnson"]]})

(def letter3
  {::id 3
   ::title "Medical Evaluation and Diagnostic Summary for Mark Thompson"
   ::content
   [:p
    [:span "Ref: 810-2735-QW "]
    [:br]
    [:span "Date: August 29, 2023"]
    [:br]
    [:span "To: Dr. Susan Parker, M.D."]
    [:br]
    [:span "Department of Cardiology"]
    [:br]
    [:span "Riverside Medical Center"]
    [:br]
    [:span "901 Health Avenue"]
    [:br]
    [:span "WellnessCity, ST 43210"]
    [:br]
    [:br]
    [:span "Subject: Medical Evaluation and Diagnostic Summary for Mark Thompson"]
    [:br]
    [:br]
    [:span "Dear Dr. Parker,"]
    [:br]
    [:span "I am writing to share the medical evaluation and treatment plan for Mr. Mark Thompson, a 67-year-old male who presented with palpitations and episodes of dizziness."]
    [:br]
    [:br]
    [:span "Chief Complaints:"]
    [:br]
    [:span "1. Palpitations"]
    [:br]
    [:span "2. Dizziness"]
    [:br]
    [:span "3. Generalized fatigue"]
    [:br]
    [:br]
    [:span "Past Medical History:"]
    [:br]
    [:span "1. Hypertension - " [:span {:id "16"} "ICD-10: I10"]]
    [:br]
    [:span "2. Type 2 Diabetes Mellitus - " [:span {:id "17"} "ICD-10: E11"]]
    [:br]
    [:br]
    [:span "Diagnostic Assessment:"]
    [:br]
    [:span "The patient underwent the following diagnostic tests:"]
    [:br]
    [:span "1. Electrocardiogram (ECG) showed atrial fibrillation."]
    [:br]
    [:span "2. Holter monitor confirmed intermittent atrial fibrillation episodes."]
    [:br]
    [:span "3. Blood tests including Complete Blood Count (CBC) and Comprehensive Metabolic Panel (CMP) were within normal limits."]
    [:br]
    [:span "Mr. Thompson was diagnosed with Atrial Fibrillation - " [:span {:id "18"} "ICD-10: I48.91"]]
    [:br]
    [:br]
    [:span "Additional ICD-10 codes for symptoms include:"]
    [:br]
    [:span "1. Palpitations - " [:span {:id "19"} "ICD-10: R00.2"]]
    [:br]
    [:span "2. Dizziness - " [:span {:id "20"} "ICD-10: R42"]]
    [:br]
    [:span "3. Generalized fatigue - " [:span {:id "21"} "ICD-10: R53"]]
    [:br]
    [:br]
    [:span "Possible External Cause:"]
    [:br]
    [:span "Given Mr. Thompson's medical history and current medications for his existing conditions, drug-induced atrial fibrillation was considered - " [:span {:id "22"} "ICD-10: T46.1X5A"]]
    [:br]
    [:br]
    [:span "Treatment Plan:"]
    [:br]
    [:span "1. Anticoagulant therapy for stroke prevention."]
    [:br]
    [:span "2. Rate control medications."]
    [:br]
    [:span "3. Lifestyle modification including a low-sodium diet and exercise."]
    [:br]
    [:br]
    [:span "Follow-Up:"]
    [:br]
    [:span "Mr. Thompson is scheduled for a follow-up appointment in two weeks to assess the efficacy of the newly prescribed anticoagulant therapy."]
    [:br]
    [:br]
    [:span "Should you require any further information, please do not hesitate to reach out."]
    [:br]
    [:span "Sincerely,"]
    [:br]
    [:span "Dr. Laura Williams, M.D."]
    [:br]
    [:span "Specialist in Internal Medicine"]
    [:br]
    [:span "Harmony Health Clinic"]
    [:br]
    [:span "567 Harmony Lane"]
    [:br]
    [:span "WellnessCity, ST 43211"]
    [:br]
    [:span "Phone: (555) 678-9101"]
    [:br]
    [:span "Email: laura.williams@harmonyhc.org"]
    [:br]
    [:span "License No: LW128735"]
    [:br]
    [:span "CC: Patient Medical Record - Mark Thompson"]]})

(def letter4
  {::id 4
   ::title "Medical Evaluation and Diagnostic Summary for Emily Davis"
   ::content
   [:p
    [:span "Ref: 345-6789-AB "]
    [:br]
    [:span "Date: August 29, 2023"]
    [:br]
    [:span "To: Dr. Jennifer Lee, M.D."]
    [:br]
    [:span "Department of Pulmonology"]
    [:br]
    [:span "University Medical Center"]
    [:br]
    [:span "1234 Healing Way"]
    [:br]
    [:span "Careville, ST 12345"]
    [:br]
    [:br]
    [:span "Subject: Medical Evaluation and Diagnostic Summary for Emily Davis"]
    [:br]
    [:br]
    [:span "Dear Dr. Lee,"]
    [:br]
    [:span "I am writing to share the results of the medical evaluation conducted on Mrs. Emily Davis, a 52-year-old female who presented with chronic cough and wheezing."]
    [:br]
    [:br]
    [:span "Chief Complaints:"]
    [:br]
    [:span "1. Chronic cough lasting for more than 8 weeks"]
    [:br]
    [:span "2. Wheezing"]
    [:br]
    [:span "3. Shortness of breath"]
    [:br]
    [:br]
    [:span "Past Medical History:"]
    [:br]
    [:span "1. No history of asthma or other respiratory diseases."]
    [:br]
    [:span "2. Ex-smoker, quit 15 years ago."]
    [:br]
    [:br]
    [:span "Diagnostic Assessment:"]
    [:br]
    [:span "Diagnostic tests included:"]
    [:br]
    [:span "1. Chest X-Ray which revealed no significant abnormalities."]
    [:br]
    [:span "2. Pulmonary Function Tests (PFT) which showed mild airflow obstruction."]
    [:br]
    [:span "3. High-Resolution Computed Tomography (HRCT) of the chest showed minor signs of bronchial thickening."]
    [:br]
    [:span "Based on these results, Mrs. Davis was diagnosed with Chronic Obstructive Pulmonary Disease (COPD) - " [:span {:id "23"} "ICD-10: J44.9"]]
    [:br]
    [:br]
    [:span "Additional ICD-10 codes for symptoms include:"]
    [:br]
    [:span "1. Chronic cough - " [:span {:id "24"} "ICD-10: R05"]]
    [:br]
    [:span "2. Wheezing - " [:span {:id "25"} "ICD-10: R06.2"]]
    [:br]
    [:span "3. Shortness of breath - " [:span {:id "26"} "ICD-10: R06.02"]]
    [:br]
    [:br]
    [:span "Possible External Cause:"]
    [:br]
    [:span "History of tobacco use may be an important factor in the patient’s current condition - " [:span {:id "27"} "ICD-10: Z72.0"]]
    [:br]
    [:br]
    [:span "Treatment Plan:"]
    [:br]
    [:span "1. Bronchodilators and inhaled corticosteroids for symptom management."]
    [:br]
    [:span "2. Lifestyle modifications including aerobic exercise."]
    [:br]
    [:span "3. Scheduled follow-up for PFT to monitor lung function."]
    [:br]
    [:br]
    [:span "Follow-Up:"]
    [:br]
    [:span "A follow-up appointment has been scheduled in six weeks to assess treatment effectiveness and make any necessary adjustments."]
    [:br]
    [:br]
    [:span "For further questions or clarifications, please do not hesitate to contact me."]
    [:br]
    [:span "Sincerely,"]
    [:br]
    [:span "Dr. William Green, M.D."]
    [:br]
    [:span "Specialist in Internal Medicine"]
    [:br]
    [:span "Wellness Health Clinic"]
    [:br]
    [:span "789 Healthy Street"]
    [:br]
    [:span "Careville, ST 12346"]
    [:br]
    [:span "Phone: (555) 321-0987"]
    [:br]
    [:span "Email: william.green@wellnesshc.org"]
    [:br]
    [:span "License No: WG256189"]
    [:br]
    [:span "CC: Patient Medical Record - Emily Davis"]]})

(def all
  [letter1
   letter2
   letter3
   letter4])
