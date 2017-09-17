(ns
  ^{:copyright "Copyright 2016-2017 SixSq Sarl"
    :license   "http://www.apache.org/licenses/LICENSE-2.0"}
  sixsq.specex.gen
  "Utilities that provide common spec generation capabilities that aren't
   included in the core library."
  (:require
    [clojure.set :as set]
    [clojure.string :as str]
    [clojure.spec.alpha :as s]
    [clojure.spec.gen.alpha :as gen]))

(def ^:private all-ascii-chars (map str (map char (range 0 256))))

(defn- regex-chars
  "Provides a list of ASCII characters that satisfy the regex pattern."
  [pattern]
  (set (filter #(re-matches pattern %) all-ascii-chars)))

(defmacro regex-string
  "Creates a string spec that matches the given regex with a generator
   that randomly selects from the ASCII characters identified by the
   char-pattern."
  [char-pattern regex]
  (let [allowed-chars (regex-chars char-pattern)]
    `(s/with-gen (s/and string? #(re-matches ~regex %))
                 (constantly (gen/fmap str/join (gen/vector (s/gen ~allowed-chars)))))))
