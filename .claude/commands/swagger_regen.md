# swagger_regen.md

> Regenerate OpenAPI/Swagger code from the latest YAML specification with parameter.

## Usage

```bash
# Step 1: Run clean build first
# See .claude/commands/clean_build.md for details
mvn clean install -DskipTests=false

# Step 2: Extract version from filename (e.g., petstore-1.0.1.yaml → 1.0.1)
VERSION=$(echo "petstore-1.0.1.yaml" | sed 's/.*-\([0-9]\+\.[0-9]\+\.[0-9]\+\)\.yaml/\1/')

# Step 3: Update pom.xml with new version
sed -i "s/<swagger\.new\.version>.*<\/swagger\.new\.version>/<swagger.new.version>$VERSION<\/swagger.new.version>/" pom.xml

# Step 4: Generate OpenAPI code
mvn clean generate-sources -Pgenerate-swagger-versions
```

## Swagger Regeneration Workflow

1. After swagger regen to the latest version the generated code appears in `target/generated-sources/openapi-v*`
2. Incorporates these regenerated Swagger changes. Keep all original method bodies and implementations intact. Only update API method signatures, models, and annotations where they differ according to the diff. Do not remove any custom logic from implementation classes