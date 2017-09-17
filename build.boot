(def +version+ "0.1.0-SNAPSHOT")

(task-options!
  pom {:project     'sixsq/specex
       :version     +version+
       :description "clojure spec extensions"
       :url         "https://github.com/sixsq/specex"
       :license     {"Apache 2.0" "http://www.apache.org/licenses/LICENSE-2.0"}
       :scm         {:url "git@github.com:SixSq/specex.git"}}
  push {:gpg-user-id "SixSq Release Manager <admin@sixsq.com>"})

(set-env!
 :resource-paths #{"src"}

 :dependencies
 '[[org.clojure/clojure "1.9.0-alpha17"]
   [adzerk/bootlaces "0.1.13" :scope "test"]
   [onetom/boot-lein-generate "0.1.3" :scope "test"]])

(require
 '[adzerk.bootlaces :refer :all]
 '[boot.lein :refer [generate]])

(bootlaces! +version+)

(deftask build
         "full build and install"
         []
         (comp (pom) (jar) (install)))
