Pod::Spec.new do |spec|
   #TODO Generate/Edit this file when trigger source code tag
  spec.name         = "KotlinNativeExample"
  spec.version      = "1.0.0"
  spec.summary      = "Shared artifacts from Kotlin/Native"
  spec.description  = "Something"

  spec.homepage     = "https://github.com/vitorhugods/KotlinNativeMobileExample"

  spec.license      = { :type => 'Apache License, Version 2.0',
                        :text => <<-LICENSE
                            Copyright (c) 2010 Google Inc.
                            Licensed under the Apache License, Version 2.0 (the "License");
                            you may not use this file except in compliance with the License.
                            You may obtain a copy of the License at
                              http://www.apache.org/licenses/LICENSE-2.0
                            Unless required by applicable law or agreed to in writing, software
                            distributed under the License is distributed on an "AS IS" BASIS,
                            WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
                            See the License for the specific language governing permissions and
                            limitations under the License.
                          LICENSE
                        }

  spec.author       = { "Vitor Hugo Schwaab" => "vitor@schwaab.xyz" }

  spec.vendored_frameworks      = "#{spec.name}.xcframework"

  spec.source       = { :git => "https://github.com/vitorhugods/KotlinNativeMobileExample", :tag => "#{spec.version}" }

end
